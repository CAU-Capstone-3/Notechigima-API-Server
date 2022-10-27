package com.capstone.notechigima.model.dao.sentence;

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
    private char type;
    private int sequenceNum;
}
