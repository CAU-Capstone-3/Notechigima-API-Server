package com.capstone.notechigima.domain.sentence_advice;

import com.capstone.notechigima.dto.comment.CommentListGetResponseDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdviceDetailEntity {
    private int adviceId;
    private char adviceType;
    private int sentenceId1;
    private String sentence1;
    private int writerId1;
    private String writerName1;
    private int sentenceId2;
    private String sentence2;
    private int writerId2;
    private String writerName2;
    private List<CommentListGetResponseDTO> comments;
}
