package com.capstone.notechigima.dao.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdviceResponseDAO {
    private int sequenceNum;
    private String content;
    private String comparisonSentence;
    private int writerId;
    private String writerName;
}
