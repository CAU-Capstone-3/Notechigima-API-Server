package com.capstone.notechigima.model.dto.topic;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class TopicResponseDTO {
    @Schema(description = "토픽 ID", defaultValue = "3")
    private int topicId;
    @Schema(description = "토픽명", defaultValue = "컴퓨터통신")
    private String title;
    @Schema(description = "최종 업데이트 시간")
    private Date updatedAt;
    @Schema(description = "분석 완료 여부", defaultValue = "false")
    private boolean analyzed;
}
