package com.capstone.notechigima.service;

import com.capstone.notechigima.config.ExceptionCode;
import com.capstone.notechigima.config.RestApiException;
import com.capstone.notechigima.domain.VisibilityStatus;
import com.capstone.notechigima.domain.comment.Comment;
import com.capstone.notechigima.domain.advice.Advice;
import com.capstone.notechigima.domain.users.User;
import com.capstone.notechigima.dto.comment.CommentPostReqeustDTO;
import com.capstone.notechigima.repository.AdviceRepository;
import com.capstone.notechigima.repository.CommentRepository;
import com.capstone.notechigima.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final UserRepository userRepository;
    private final AdviceRepository adviceRepository;
    private final CommentRepository commentRepository;

    public void postComment(int adviceId, CommentPostReqeustDTO body) throws RestApiException {
        Advice advice = adviceRepository.findById(adviceId).orElseThrow(() -> {
            throw new RestApiException(ExceptionCode.ERROR_NOT_FOUND_RESOURCE);
        });
        User user = userRepository.findById(body.getUserId()).orElseThrow(() -> {
            throw new RestApiException(ExceptionCode.ERROR_NOT_FOUND_RESOURCE);
        });

        Comment comment = Comment.builder()
                .advice(advice)
                .user(user)
                .content(body.getContent())
                .status(VisibilityStatus.VISIBLE)
                .build();

        commentRepository.save(comment);
    }
}
