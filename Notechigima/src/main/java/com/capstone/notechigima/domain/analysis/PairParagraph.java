package com.capstone.notechigima.domain.analysis;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
/**
 * 여러 명의 문단을 같이 묶어주는 클래스
 */
public class PairParagraph {

    private final List<Paragraph> paragraphs = new ArrayList<>();
    private boolean haveContradiction = false;
    private final List<String> keywords = new ArrayList<>();

    public String getMergedString() {
        StringBuilder sb = new StringBuilder();
        this.paragraphs
                .forEach(paragraph -> {
                    sb.append(paragraph.toString());
                });
        return sb.toString();
    }

    public Paragraph getCentral() {
        return paragraphs.get(0);
    }

    public void addAllParagraphs(List<Paragraph> paragraphs) {
        this.paragraphs.addAll(paragraphs);
    }

    public void addParagraph(Paragraph paragraph) {
        this.paragraphs.add(paragraph);
    }

    public void addAllKeywords(List<String> keywords) {
        for (String keyword : keywords) {
            this.keywords.addAll(List.of(keyword.split(" ")));
        }
    }

    public void setContradiction() {
        this.haveContradiction = true;
    }

}
