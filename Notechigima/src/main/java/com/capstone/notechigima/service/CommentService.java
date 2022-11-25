package com.capstone.notechigima.service;

import com.capstone.notechigima.config.BaseException;
import com.capstone.notechigima.dto.comment.CommentPostReqeustDTO;

public interface CommentService {

    int postComment(int adviceId, CommentPostReqeustDTO request) throws BaseException;

}
