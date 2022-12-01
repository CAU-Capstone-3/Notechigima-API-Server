package com.capstone.notechigima.dto.topic;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TopicPostRequestDTO {
    @Schema(description = "과목 ID", defaultValue = "1")
    private int subjectId;
    @Schema(description = "토픽명", defaultValue = "2-4. 오류검출")
    private String title;

    @Builder
    public TopicPostRequestDTO(int subjectId, String title) {
        this.subjectId = subjectId;
        this.title = title;
    }
}
