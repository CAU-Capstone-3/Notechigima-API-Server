package com.capstone.notechigima.model.dto.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdviceResponseDTO {
    private int adviceId;
    private String advice;
    private int sentenceId;
    private String sentence;
    private int writerId;
    private String writerName;
}