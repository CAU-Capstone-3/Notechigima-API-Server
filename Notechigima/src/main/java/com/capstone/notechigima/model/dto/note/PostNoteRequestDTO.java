package com.capstone.notechigima.model.dto.note;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostNoteRequestDTO {
    @Schema(description = "작성자 ID", defaultValue = "3")
    private int writerId;
    @Schema(description = "노트 내용", defaultValue = "노트 내용입니다.")
    private String content;
}
