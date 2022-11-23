package com.capstone.notechigima.model.dto.note;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostNoteRequestDTO {

    @Schema(description = "토픽 ID", defaultValue = "1")
    private int topicId;
    @Schema(description = "유저 ID", defaultValue = "3")
    private int userId;
    @Schema(description = "노트 내용", defaultValue = "노트 내용입니다.")
    private String content;
}
