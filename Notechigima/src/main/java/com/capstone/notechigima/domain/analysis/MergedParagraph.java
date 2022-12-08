package com.capstone.notechigima.domain.analysis;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class MergedParagraph {
    private final boolean contradiction;
    private final List<Paragraph> represent;
    private final List<Omission> omissions;

    @Builder
    @Data
    public static class Omission {
        private final int noteId;
        private final List<String> keywords;
    }
}
