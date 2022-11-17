package com.capstone.notechigima.model.dao.advice;

import com.capstone.notechigima.model.dao.comment.CommentEntity;
import com.capstone.notechigima.model.dao.sentence.SentenceWithWriterEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdviceDetailEntity {
    private int adviceId;
    private char adviceType;
    SentenceWithWriterEntity sentence1;
    SentenceWithWriterEntity sentence2;
    private List<CommentEntity> comments;
}
