package com.capstone.notechigima.model.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AdviceEntity {
    private int adviceId;
    private int sentenceFrom;
    private int sentenceTo;
    private char type;
}
