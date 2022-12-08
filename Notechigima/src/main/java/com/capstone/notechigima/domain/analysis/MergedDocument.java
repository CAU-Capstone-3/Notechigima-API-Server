package com.capstone.notechigima.domain.analysis;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MergedDocument {
    private final List<MergedParagraph> paragraphs = new ArrayList<>();
    private final List<Contribution> contributions = new ArrayList<>();

    public void addParagraph(MergedParagraph paragraph) {
        this.paragraphs.add(paragraph);
    }

    public void addContributions(Contribution contribution) {
        this.contributions.add(contribution);
    }
}
