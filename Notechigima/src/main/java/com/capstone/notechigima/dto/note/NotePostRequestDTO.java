package com.capstone.notechigima.dto.note;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotePostRequestDTO {
    @Schema(description = "노트를 작성할 토픽 ID", defaultValue = "2")
    private int topicId;
    @Schema(description = "유저 ID", defaultValue = "3")
    private int userId;
    @Schema(description = "노트 내용", defaultValue = "노트 내용입니다.")
    private String content;
}
