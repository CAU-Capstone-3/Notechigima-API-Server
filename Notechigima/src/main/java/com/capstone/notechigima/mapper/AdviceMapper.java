package com.capstone.notechigima.mapper;

import com.capstone.notechigima.domain.comment.Comment;
import com.capstone.notechigima.domain.sentence_advice.Advice;
import com.capstone.notechigima.domain.sentence_advice.AdviceType;
import com.capstone.notechigima.dto.advice.AdviceGetResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface AdviceMapper {
    AdviceMapper INSTANCE = Mappers.getMapper(AdviceMapper.class);
    String IS_CONTRADICTION = "상반된 문장이 있어요.";

    // TODO comment 랑 같이 구현
    default AdviceGetResponseDTO toAdviceGetResponseDTO(Advice advice, List<Comment> comments) {
        return AdviceGetResponseDTO.builder()
                .adviceId(advice.getAdviceId())
                .sentence1(SentenceMapper.INSTANCE.toSentenceGetResponseDTO(advice.getSentence1()))
                .sentence2(SentenceMapper.INSTANCE.toSentenceGetResponseDTO(advice.getSentence2()))
                .advice(advice.getAdviceType() == AdviceType.CONTRADICTION ? IS_CONTRADICTION : "알려지지 않은 상태")
                .comments(
                        comments.stream()
                                        .map(entity -> CommentMapper.INSTANCE.toCommentListGetResponseDTO(entity))
                                .collect(Collectors.toList())
                )
                .build();
    }
}
