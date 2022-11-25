package com.capstone.notechigima.dto.sentence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SentenceGetResponseDTO {
    private int sentenceId;
    private String sentence;
    private int writerId;
    private String writerName;

    @Builder
    public SentenceGetResponseDTO(int sentenceId, String sentence, int writerId, String writerName) {
        this.sentenceId = sentenceId;
        this.sentence = sentence;
        this.writerId = writerId;
        this.writerName = writerName;
    }
}
