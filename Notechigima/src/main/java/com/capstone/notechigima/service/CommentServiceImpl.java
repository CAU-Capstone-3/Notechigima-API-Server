package com.capstone.notechigima.service;

import com.capstone.notechigima.config.BaseException;
import com.capstone.notechigima.model.dao.comment.CommentWriteEntity;
import com.capstone.notechigima.model.dto.comment.PostCommentRequestDTO;
import com.capstone.notechigima.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public int postComment(PostCommentRequestDTO body) throws BaseException {
        return commentRepository.postComment(
                new CommentWriteEntity(body.getUserId(), body.getAdviceId(), body.getContent())
        );
    }
}
