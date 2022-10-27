package com.capstone.notechigima.model.dto.sentence;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SentenceResponseDTO {
    private String content;
    private int sequenceNum;
    private char sentenceType;
}
