package com.capstone.notechigima.dto.topic;

import com.capstone.notechigima.domain.topic.TopicAnalyzedType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class TopicResponseDTO {
    @Schema(description = "토픽 ID", defaultValue = "3")
    private int topicId;
    @Schema(description = "토픽명", defaultValue = "컴퓨터통신")
    private String title;
    @Schema(description = "최종 업데이트 시간")
    private LocalDateTime updatedAt;
    @Schema(description = "분석 완료 여부", defaultValue = "false")
    private TopicAnalyzedType analyzed;

    @Builder
    public TopicResponseDTO(int topicId, String title, LocalDateTime updatedAt, TopicAnalyzedType analyzed) {
        this.topicId = topicId;
        this.title = title;
        this.updatedAt = updatedAt;
        this.analyzed = analyzed;
    }
}
