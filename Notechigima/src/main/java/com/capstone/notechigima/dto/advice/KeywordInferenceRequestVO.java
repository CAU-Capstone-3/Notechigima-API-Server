package com.capstone.notechigima.dto.advice;

import lombok.Builder;
import lombok.Getter;

@Getter
public class KeywordInferenceRequestVO {
    private final String document;
    private final int topN;
    private final double diversity;

    @Builder
    public KeywordInferenceRequestVO(String document, int topN, double diversity) {
        this.document = document;
        this.topN = topN;
        this.diversity = diversity;
    }
}
