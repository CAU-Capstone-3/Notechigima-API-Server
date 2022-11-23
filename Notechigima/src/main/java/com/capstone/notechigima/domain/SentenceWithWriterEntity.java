package com.capstone.notechigima.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SentenceWithWriterEntity {
    private int sentenceId;
    private String content;
    private int writerId;
    private String writerName;
}
