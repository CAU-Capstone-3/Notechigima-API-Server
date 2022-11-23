package com.capstone.notechigima.service;

import com.capstone.notechigima.config.BaseException;
import com.capstone.notechigima.model.comment.PostCommentRequestDTO;

public interface CommentService {

    int postComment(int adviceId, PostCommentRequestDTO request) throws BaseException;

}
