package com.capstone.notechigima.model.dto.sentence;

import com.capstone.notechigima.model.dao.sentence.SentenceEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SentenceResponseDTO {
    private String content;
    private int sequenceNum;
    private char sentenceType;

    public static SentenceResponseDTO from(SentenceEntity sentenceEntity) {
        return new SentenceResponseDTO(sentenceEntity.getContent(), sentenceEntity.getSequenceNum(), sentenceEntity.getType());
    }
}
