package com.capstone.notechigima.service;

import com.capstone.notechigima.config.BaseException;
import com.capstone.notechigima.domain.comment.CommentWriteEntity;
import com.capstone.notechigima.dto.comment.PostCommentRequestDTO;
import com.capstone.notechigima.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public int postComment(int adviceId, PostCommentRequestDTO body) throws BaseException {
        return commentRepository.postComment(
                new CommentWriteEntity(body.getUserId(), adviceId, body.getContent())
        );
    }
}
