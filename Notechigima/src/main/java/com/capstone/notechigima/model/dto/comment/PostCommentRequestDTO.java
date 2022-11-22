package com.capstone.notechigima.model.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostCommentRequestDTO {

    @Schema(description = "댓글 작성할 분석결과 ID", defaultValue = "13")
    private int adviceId;
    @Schema(description = "유저 ID", defaultValue = "1")
    private int userId;
    @Schema(description = "댓글 내용", defaultValue = "저는 이렇게 생각해요.")
    private String content;
}
