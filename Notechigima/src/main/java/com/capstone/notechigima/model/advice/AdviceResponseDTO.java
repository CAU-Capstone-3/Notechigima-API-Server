package com.capstone.notechigima.model.advice;

import com.capstone.notechigima.domain.comment.CommentDetailEntity;
import com.capstone.notechigima.model.sentence.SentenceVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AdviceResponseDTO {
    @Schema(description = "분석 결과항목 ID", defaultValue = "1")
    private int adviceId;
    @Schema(description = "분석 결과", defaultValue = "상반되는 문장이 있어요.")
    private String advice;
    @Schema(description = "문장 1")
    private SentenceVO sentence1;
    @Schema(description = "문장 2")
    private SentenceVO sentence2;
    @Schema(description = "댓글 목록")
    private List<CommentDetailEntity> comments;
}
