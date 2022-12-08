package com.capstone.notechigima.domain.analysis;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParsedSentence {
    private String sentence;
    private boolean newLined;
}
