package com.capstone.notechigima.mapper;

import com.capstone.notechigima.domain.comment.Comment;
import com.capstone.notechigima.domain.advice.Advice;
import com.capstone.notechigima.domain.advice.AdviceType;
import com.capstone.notechigima.dto.advice.AdviceGetResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

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
}
