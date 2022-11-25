package com.capstone.notechigima.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
public class CommentListGetResponseDTO {
    @Schema(description = "댓글 ID", defaultValue = "12")
    private int commentId;
    @Schema(description = "댓글 작성자 ID", defaultValue = "1")
    private int userId;
    @Schema(description = "작성자 이름", defaultValue = "김형기")
    private String userName;
    @Schema(description = "댓글 내용", defaultValue = "이해가 잘 안되는 것 같아요.")
    private String content;
    @Schema(description = "최종 수정 시간")
    private LocalDateTime updatedAt;

    @Builder
    public CommentListGetResponseDTO(int commentId, int userId, String userName, String content, LocalDateTime updatedAt) {
        this.commentId = commentId;
        this.userId = userId;
        this.userName = userName;
        this.content = content;
        this.updatedAt = updatedAt;
    }
}
