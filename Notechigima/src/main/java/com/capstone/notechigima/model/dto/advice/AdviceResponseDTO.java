package com.capstone.notechigima.model.dto.advice;

import com.capstone.notechigima.model.dao.comment.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AdviceResponseDTO {
    private int adviceId;
    private String advice;
    private int sentenceId;
    private String sentence;
    private int writerId;
    private String writerName;
    private List<CommentEntity> comments;
}
