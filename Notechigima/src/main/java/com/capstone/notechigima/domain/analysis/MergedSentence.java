package com.capstone.notechigima.domain.analysis;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class MergedSentence {

    private boolean success;
    private boolean isContradiction;
    private List<Sentence> represent;
    private List<Omission> omissions;

    @Builder
    @Data
    public static class Omission {
        private final int noteId;
        private final List<String> keywords;
    }

    @Builder
    @Data
    public static class Sentence {
        private final int noteId;
        private final String content;
    }
}
