package com.capstone.notechigima.service;

import com.capstone.notechigima.domain.comment.Comment;
import com.capstone.notechigima.domain.sentence_advice.Advice;
import com.capstone.notechigima.dto.advice.AdviceGetResponseDTO;
import com.capstone.notechigima.mapper.AdviceMapper;
import com.capstone.notechigima.repository.AdviceRepository;
import com.capstone.notechigima.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdviceServiceJPA {

    private final AdviceRepository adviceRepository;
    private final CommentRepository commentRepository;

    public List<AdviceGetResponseDTO> getAdviceList(int topicId) {
        List<Advice> advices = adviceRepository.findAllBySentence1_Note_Topic_TopicId(topicId);
        HashMap<Advice, List<Comment>> commentMap = new HashMap<>();

        // get comments
        advices.forEach(entity -> {
            List<Comment> comments = commentRepository.findAllByAdvice_AdviceId(entity.getAdviceId());
            commentMap.put(entity, comments);
        });

        return advices.stream().map(entity ->
                AdviceMapper.INSTANCE.toAdviceGetResponseDTO(entity, commentMap.get(entity)))
                .collect(Collectors.toList());
    }
}