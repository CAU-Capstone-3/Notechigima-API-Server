package com.capstone.notechigima.dto.advice;

import com.capstone.notechigima.dto.comment.CommentListGetResponseDTO;
import com.capstone.notechigima.dto.sentence.SentenceGetResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class AdviceGetResponseDTO {
    @Schema(description = "분석 결과항목 ID", defaultValue = "1")
    private int adviceId;
    @Schema(description = "분석 결과", defaultValue = "상반되는 문장이 있어요.")
    private String advice;
    @Schema(description = "분석 결과")
    private String content;
    @Schema(description = "댓글 목록")
    private List<CommentListGetResponseDTO> comments;

    @Builder
    public AdviceGetResponseDTO(int adviceId, String advice, String content, List<CommentListGetResponseDTO> comments) {
        this.adviceId = adviceId;
        this.advice = advice;
        this.content = content;
        this.comments = comments;
    }
}
