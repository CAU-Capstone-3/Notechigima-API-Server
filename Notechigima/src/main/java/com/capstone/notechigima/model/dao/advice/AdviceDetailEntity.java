package com.capstone.notechigima.model.dao.advice;

import com.capstone.notechigima.model.dao.comment.CommentEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdviceDetailEntity {
    private int adviceId;
    private char adviceType;
    private int sentenceId;
    private String sentence;
    private int writerId;
    private String writerName;
    private List<CommentEntity> comments;
}
