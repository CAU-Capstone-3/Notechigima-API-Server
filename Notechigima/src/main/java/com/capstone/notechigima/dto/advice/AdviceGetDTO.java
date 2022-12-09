package com.capstone.notechigima.dto.advice;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AdviceGetDTO {

    private int adviceId;
    private boolean isContradiction;
    private List<MergedSentenceDTO> sentences;
    private List<String> advices;

    @Data
    @Builder
    public static class MergedSentenceDTO {
        private int writerId;
        private String writerName;
        private String content;
    }

}
