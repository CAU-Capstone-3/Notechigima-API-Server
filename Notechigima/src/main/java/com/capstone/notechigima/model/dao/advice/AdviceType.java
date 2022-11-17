package com.capstone.notechigima.model.dao.advice;

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
