package com.capstone.notechigima.model.sentence;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SentenceResponseModel {
    private String content;
    private int sequenceNum;
    private char sentenceType;
}
