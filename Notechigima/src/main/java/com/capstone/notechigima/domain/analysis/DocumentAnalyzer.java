package com.capstone.notechigima.domain.analysis;

import java.util.*;
import java.util.stream.Collectors;

public class DocumentAnalyzer {

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
    public DocumentAnalyzer(Document centralDocument, List<Document> documents) {
        this.centralDocument = centralDocument;
        this.documents = documents;
        init();
    }

    private void init() {
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


    public MergedDocument mergeDocument() {
        MergedDocument result = new MergedDocument();

        for (PairParagraph pair : pairParagraphs) {
            List<Paragraph> represent = new ArrayList<>();
            represent.add(containMaxKeywords(pair));
            List<MergedParagraph.Omission> omissions = new ArrayList<>();

            if (!pair.isHaveContradiction()) {
                for (Paragraph p : pair.getParagraphs()) {
                    List<String> omissionKeywords = omissionKeywords(p, pair.getKeywords());

                    MergedParagraph.Omission omission = MergedParagraph.Omission
                            .builder()
                            .noteId(p.getNoteId())
                            .keywords(omissionKeywords)
                            .build();

                    omissions.add(omission);
                }
            } else {
                List<Paragraph> others = pair.getParagraphs().stream()
                        .filter(paragraph -> paragraph.getNoteId() != represent.get(0).getNoteId()).toList();
                represent.addAll(others);
            }

            MergedParagraph mergedParagraph = MergedParagraph.builder()
                    .contradiction(pair.isHaveContradiction())
                    .represent(represent)
                    .omissions(omissions)
                    .build();

            result.addParagraph(mergedParagraph);
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

    private static Paragraph containMaxKeywords(PairParagraph pair) {
        Paragraph result = null;
        int max = 0;
        for (Paragraph paragraph : pair.getParagraphs()) {
            int containKeywords = numOfContainKeywords(paragraph, pair.getKeywords());
            if (max < containKeywords) {
                result = paragraph;
                max = containKeywords;
            }
        }
        return result;
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


}
