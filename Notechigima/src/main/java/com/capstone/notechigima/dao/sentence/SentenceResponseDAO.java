package com.capstone.notechigima.dao.sentence;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SentenceResponseDAO {
    private String content;
    private int sequenceNum;
    private char sentenceType;
}
