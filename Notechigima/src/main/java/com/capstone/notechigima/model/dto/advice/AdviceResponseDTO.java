package com.capstone.notechigima.model.dto.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdviceResponseDTO {
    private int sequenceNum;
    private String content;
    private String comparisonSentence;
    private int writerId;
    private String writerName;
}
