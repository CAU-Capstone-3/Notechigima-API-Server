package com.capstone.notechigima.domain.advice;

public enum AdviceType {
    CONTRADICTION('C');

    private char type;

    AdviceType(char type) {
        this.type = type;
    }

    public char getType() {
        return this.type;
    }
}
