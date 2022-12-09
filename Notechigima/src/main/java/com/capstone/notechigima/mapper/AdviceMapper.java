package com.capstone.notechigima.mapper;

import com.capstone.notechigima.domain.advice_sentence.AdviceSentence;
import com.capstone.notechigima.domain.analysis.MergedSentence;
import com.capstone.notechigima.domain.comment.Comment;
import com.capstone.notechigima.domain.advice.Advice;
import com.capstone.notechigima.domain.advice.AdviceType;
import com.capstone.notechigima.dto.advice.AdviceGetDTO;
import com.capstone.notechigima.dto.advice.AdviceGetResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface AdviceMapper {
    AdviceMapper INSTANCE = Mappers.getMapper(AdviceMapper.class);

    default AdviceGetResponseDTO toAdviceGetResponseDTO(Advice advice, List<Comment> comments) {
        return AdviceGetResponseDTO.builder()
                .adviceId(advice.getAdviceId())
                .comments(
                        comments.stream()
                                        .map(entity -> CommentMapper.INSTANCE.toCommentListGetResponseDTO(entity))
                                .collect(Collectors.toList())
                )
                .content(advice.getContent())
                .build();
    }

    default AdviceGetDTO toAdviceGetDTO(Advice advice, List<Comment> comments) {

        List<AdviceGetDTO.MergedSentenceDTO> sentences =
                advice.getAdviceSentence().stream()
                        .map(sent -> AdviceGetDTO.MergedSentenceDTO.builder()
                                .writerId(sent.getUser().getUserId())
                                .writerName(sent.getUser().getNickname())
                                .content(sent.getContent())
                                .build()).toList();

        return AdviceGetDTO.builder()
                .adviceId(advice.getAdviceId())
                .isContradiction(advice.getAdviceType() == AdviceType.CONTRADICTION)
                .sentences(sentences)
                .advices(Arrays.stream(advice.getContent().split("\n")).toList())
                .comments(
                        comments.stream()
                                .map(CommentMapper.INSTANCE::toCommentListGetResponseDTO)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
