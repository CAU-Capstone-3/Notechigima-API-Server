package com.capstone.notechigima.domain.sentence;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SentenceType {

    MAJOR_HEAD("MAJOR_HEAD", "1차 소제목"),
    SUB_HEAD("SUB_HEAD", "2차 소제목"),
    MINOR_HEAD("MINOR_HEAD", "3차 소제목"),
    PLAIN("PLAIN", "평문");

    private final String key;
    private final String title;
}
