package com.capstone.notechigima.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SentenceEntity {
    private int sentenceId;
    private int noteId;
    private String content;
    private char sentenceType;
    private int sequenceNum;
}
