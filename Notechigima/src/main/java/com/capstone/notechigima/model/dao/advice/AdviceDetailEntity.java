package com.capstone.notechigima.model.dao.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AdviceDetailEntity {
    private int adviceId;
    private char adviceType;
    private int sentenceId;
    private String sentence;
    private int writerId;
    private String writerName;
}
