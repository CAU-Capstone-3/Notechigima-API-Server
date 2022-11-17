package com.capstone.notechigima.model.dto.sentence;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SentenceVO {
    private int sentenceId;
    private String sentence;
    private int writerId;
    private String writerName;
}
