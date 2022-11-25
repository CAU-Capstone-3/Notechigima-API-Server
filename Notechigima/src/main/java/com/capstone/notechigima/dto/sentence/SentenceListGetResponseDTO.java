package com.capstone.notechigima.dto.sentence;

import com.capstone.notechigima.domain.sentence.SentenceType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SentenceListGetResponseDTO {
    @Schema(description = "문장 내용", defaultValue = "문장 내용은 이러이러합니다.")
    private String content;
    @Schema(description = "문장 순서", defaultValue = "55")
    private int sequenceNum;
    @Schema(description = "문장 타입", defaultValue = "PLAIN")
    private SentenceType sentenceType;

    @Builder
    public SentenceListGetResponseDTO(String content, int sequenceNum, SentenceType sentenceType) {
        this.content = content;
        this.sequenceNum = sequenceNum;
        this.sentenceType = sentenceType;
    }
}
