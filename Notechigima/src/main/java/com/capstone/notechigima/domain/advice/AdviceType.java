package com.capstone.notechigima.domain.advice;

import lombok.Getter;

@Getter
public enum AdviceType {
    CONTRADICTION("CONTRADICTION"),
    NONE("NONE"),
    OMISSION("OMISSION");

    private final String key;

    AdviceType(String key) {
        this.key = key;
    }

}
