package com.capstone.notechigima.model.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdviceResponseModel {
    private int sequenceNum;
    private String content;
    private String comparisonSentence;
    private int writerId;
    private String writerName;
}
