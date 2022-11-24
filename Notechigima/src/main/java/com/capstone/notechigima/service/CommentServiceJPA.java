package com.capstone.notechigima.service;

import com.capstone.notechigima.config.BaseException;
import com.capstone.notechigima.domain.VisibilityStatus;
import com.capstone.notechigima.domain.comment.Comment;
import com.capstone.notechigima.domain.comment.CommentWriteEntity;
import com.capstone.notechigima.domain.sentence_advice.Advice;
import com.capstone.notechigima.domain.users.User;
import com.capstone.notechigima.dto.comment.CommentPostReqeustDTO;
import com.capstone.notechigima.repository.AdviceRepository;
import com.capstone.notechigima.repository.CommentRepository;
import com.capstone.notechigima.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceJPA {

    private final UserRepository userRepository;
    private final AdviceRepository adviceRepository;
    private final CommentRepository commentRepository;

    public void postComment(int adviceId, CommentPostReqeustDTO body) throws BaseException {
        Advice advice = adviceRepository.findById(adviceId).orElseThrow();
        User user = userRepository.findById(body.getUserId()).orElseThrow();

        Comment comment = Comment.builder()
                .advice(advice)
                .user(user)
                .content(body.getContent())
                .status(VisibilityStatus.VISIBLE)
                .build();

        commentRepository.save(comment);
    }
}
