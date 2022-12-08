package com.capstone.notechigima.domain.analysis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentAnalyzer {

    private final List<Document> documents;
    private final NounParser nounParser;

    // userId : point -> contribution
    private final Map<Integer, Integer> points = new HashMap<>();
    private int totalCount = 0;

    public DocumentAnalyzer(List<Document> documents, NounParser nounParser) {
        this.documents = documents;
        this.nounParser = nounParser;
        analysis();
    }

    private void analysis() {
//        documents.get(0).getClusters().get(0).getSentences().get(0).getSentence()
    }

    public MergedDocument getMergedDocument() {
return null;
    }

    public List<Contribution> getContribution() {
return null;
    }

}
