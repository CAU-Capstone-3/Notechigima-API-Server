package com.capstone.notechigima.dto.sentence;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SentenceResponseDTO {
    @Schema(description = "문장 내용", defaultValue = "문장 내용은 이러이러합니다.")
    private String content;
    @Schema(description = "문장 순서", defaultValue = "55")
    private int sequenceNum;
    @Schema(description = "문장 타입", defaultValue = "a")
    private char sentenceType;
}
