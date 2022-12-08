package com.capstone.notechigima.domain.analysis;

import java.util.*;

public class DocumentAnalyzer {

    private final Document centralDocument;
    private final List<Document> documents;

    private Document mergedDocument;

    private final List<List<Paragraph>> pairParagraphs = new ArrayList<>();
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
        analysis();
    }

    private void analysis() {
        for (Paragraph paragraph : centralDocument.getParagraphs()) {
            List<Paragraph> pair = new ArrayList<>();
            pair.add(paragraph);

            // other documents
            for (Document cmpDocument : documents) {
                pair.add(findSameParagraph(paragraph, cmpDocument));
            }

            pairParagraphs.add(pair);
        }

    }

    public List<List<Paragraph>> getPairParagraphs() {
        return Collections.unmodifiableList(pairParagraphs);
    }

    public void setContradictionAt(int paragraphIdx) {

    }

    public void setKeywordAt(int paragraphIdx, List<String> keywords) {

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

    public MergedDocument getMergedDocument() {
return null;
    }

    public List<Contribution> getContribution() {
return null;
    }

}
