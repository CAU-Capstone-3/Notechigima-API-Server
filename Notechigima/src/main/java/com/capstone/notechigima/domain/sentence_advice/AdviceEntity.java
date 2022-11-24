package com.capstone.notechigima.domain.sentence_advice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AdviceEntity {
    private int adviceId;
    private int sentence1Id;
    private int sentence2Id;
    private char adviceType;
}
