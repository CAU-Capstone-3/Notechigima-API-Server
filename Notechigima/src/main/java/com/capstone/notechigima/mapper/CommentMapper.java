package com.capstone.notechigima.mapper;

import com.capstone.notechigima.domain.comment.Comment;
import com.capstone.notechigima.dto.comment.CommentListGetResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    default CommentListGetResponseDTO toCommentListGetResponseDTO(Comment comment) {
        return CommentListGetResponseDTO.builder()
                .commentId(comment.getCommentId())
                .userId(comment.getUser().getUserId())
                .userName(comment.getUser().getNickname())
                .content(comment.getContent())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }

}
