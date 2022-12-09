package com.capstone.notechigima.domain.analysis;

import com.capstone.notechigima.dto.advice.KeywordInferenceRequestVO;
import com.capstone.notechigima.dto.advice.NliInferenceRequestVO;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

public class DocumentAnalyzer {

    private final String nliUri;
    public static final String POSTFIX_URL_NLI = "/nli";
    public static final String POSTFIX_URL_KEYWORD = "/keyword";
    public static final int KEYWORD_TOP_N = 5;
    public static final double KEYWORD_DIVERSITY = 0.7;

    private final Document centralDocument;
    private final List<Document> documents;


    private final List<PairParagraph> pairParagraphs = new ArrayList<>();
    // userId : point -> contribution
    private final Map<Integer, Integer> points = new HashMap<>();
    private int totalCount = 0;

    /**
     *
     * @param centralDocument 문단 순서 파악의 중심이 되는 문서
     * @param documents 분석할 문서들 (centralDocument 포함 불가)
     */
    public DocumentAnalyzer(String nliUri, Document centralDocument, List<Document> documents) {
        this.nliUri = nliUri;
        this.centralDocument = centralDocument;
        this.documents = documents;
        init();
    }

    /**
     * 그룹장의 문단 순서대로
     * 각 그룹원들의 문단을 함께 묶는다.
     */
    private void init() {
        // 그룹장이 가진 문단의 순서대로 진행
        for (Paragraph paragraph : centralDocument.getParagraphs()) {
            PairParagraph pair = new PairParagraph();
            pair.addParagraph(paragraph);

            // other documents
            for (Document cmpDocument : documents) {
                pair.addParagraph(findSameParagraph(paragraph, cmpDocument));
            }

            pairParagraphs.add(pair);
        }

    }

    public List<PairParagraph> getPairParagraphs() {
        return Collections.unmodifiableList(pairParagraphs);
    }

    private Paragraph findSameParagraph(Paragraph paragraph, Document inDocument) {
        Paragraph maxPar = inDocument.getParagraphs().get(0);
        int max = 0;

        for (Paragraph cmpPar : inDocument.getParagraphs()) {
            int sameNouns = paragraph.countSameNouns(cmpPar.getNouns());

            if (max < sameNouns) {
                max = sameNouns;
                maxPar = cmpPar;
            }
        }
        return maxPar;
    }


    public MergedDocument merge() {
        MergedDocument merged = new MergedDocument();

        for (PairParagraph pair : pairParagraphs) {
            List<String> keywords = getKeywords(5, pair.getMergedString());
            // 대표 문단 설정
            Paragraph presentParagraph = isMaxKeywords(pair, keywords);

            // 분석 결과로 묶기
            MergedSentence mergedSentence = mergeSentence(pair, presentParagraph, keywords);

            merged.addSentence(mergedSentence);
        }

        return merged;
    }

    private MergedSentence mergeSentence(PairParagraph pair, Paragraph representPar, List<String> keywords) {
        List<MergedSentence.Sentence> represent = new ArrayList<>();
        List<MergedSentence.Omission> omissions = new ArrayList<>();

        represent.add(MergedSentence.Sentence.builder()
                .noteId(representPar.getNoteId())
                .content(representPar.toString())
                .build()
        );

        // 모순된 문장이 있을 때
        if (haveContradiction(pair)) {
            // 나머지 모든 문단 추가
            pair.getParagraphs().stream()
                    .filter(paragraph -> paragraph.getNoteId() != representPar.getNoteId())
                    .forEach(paragraph -> {
                        represent.add(
                                MergedSentence.Sentence.builder()
                                        .noteId(paragraph.getNoteId())
                                        .content(paragraph.toString())
                                        .build()
                        );
                    });

            return MergedSentence.builder()
                    .isContradiction(true)
                    .represent(represent)
                    .build();
        }

        // 누락된 키워드 파악
        for (Paragraph paragraph : pair.getParagraphs()) {
            List<String> omKeywords = omissionKeywords(paragraph, keywords);
            MergedSentence.Omission omission = MergedSentence.Omission.builder()
                    .keywords(omKeywords)
                    .noteId(paragraph.getNoteId())
                    .build();

            omissions.add(omission);
        }

        // 누락 없이 모두 잘 쓴 경우
        if (omissions.isEmpty()) {
            return MergedSentence.builder()
                    .success(true)
                    .represent(represent)
                    .omissions(omissions)
                    .build();
        }
        // 누락 있는 경우
        return MergedSentence.builder()
                .omissions(omissions)
                .represent(represent)
                .build();
    }

    private static Paragraph isMaxKeywords(PairParagraph pair, List<String> keywords) {
        Paragraph result = null;
        int max = -1;
        for (Paragraph paragraph : pair.getParagraphs()) {
            int contains = numOfContainKeywords(paragraph, keywords);
            if (max < contains) {
                result = paragraph;
                max = contains;
            }
        }
        return result;
    }

    private static List<String> omissionKeywords(Paragraph paragraph, List<String> keywords) {
        List<String> omissions = new ArrayList<>();
        for (String keyword : keywords) {
            if (!paragraph.toString().contains(keyword)) {
                omissions.add(keyword);
            }
        }
        return omissions;
    }

    private static int numOfContainKeywords(Paragraph paragraph, List<String> keywords) {
        int count = 0;
        String str = paragraph.toString();

        for (String keyword: keywords) {
            if (str.contains(keyword)) {
                count++;
            }
        }
        return count;
    }

    private boolean haveContradiction(PairParagraph pair) {
        List<Paragraph> paragraphs = pair.getParagraphs();
        for (int i = 0; i < paragraphs.size(); i++) {
            for (int j = i + 1; j < paragraphs.size(); j++) {
                Paragraph parA = paragraphs.get(i);
                Paragraph parB = paragraphs.get(j);

                for (String sentA : parA.getSentences()) {
                    for (String sentB : parB.getSentences()) {
                        if (isContradiction(sentA, sentB)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Async("threadPoolTaskExecutor")
    public boolean isContradiction(String sent1, String sent2) {
        System.out.println("request nli " + sent1 + " " + sent2);

        RestTemplate restTemplate = new RestTemplate();
        URI uri = UriComponentsBuilder
                .fromUriString(nliUri + POSTFIX_URL_NLI)
                .encode()
                .build()
                .toUri();
        NliInferenceRequestVO request = new NliInferenceRequestVO(
                sent1, sent2
        );
        String response = restTemplate.postForObject(uri, request, String.class);

        try {
            JSONObject jsonObject = new JSONObject(response);
            String result = jsonObject.getString("result");
            if (result.equals("contradiction"))
                return true;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Async("threadPoolTaskExecutor")
    public List<String> getKeywords(int topN, String sentence) {
        System.out.println("request keywords : " + sentence);

        RestTemplate restTemplate = new RestTemplate();
        URI uri = UriComponentsBuilder
                .fromUriString(nliUri + POSTFIX_URL_KEYWORD)
                .encode()
                .build()
                .toUri();
        KeywordInferenceRequestVO request = KeywordInferenceRequestVO.builder()
                .document(sentence)
                .topN(topN)
                .diversity(KEYWORD_DIVERSITY)
                .build();
        String response = restTemplate.postForObject(uri, request, String.class);

        List<String> keywords = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(response);
            for (Object o : jsonObject.getJSONArray("keywords")) {
                keywords.add(o.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return keywords;
    }

}
