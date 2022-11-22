package com.capstone.notechigima.controller;

import com.capstone.notechigima.config.BaseException;
import com.capstone.notechigima.config.BaseResponse;
import com.capstone.notechigima.config.BaseResponseStatus;
import com.capstone.notechigima.model.dto.comment.PostCommentRequestDTO;
import com.capstone.notechigima.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "comment", description = "댓글 API")
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    @Operation(summary = "댓글 작성", description = "분석 결과에 댓글 작성")
    public BaseResponse postComment(@RequestBody PostCommentRequestDTO request) throws BaseException {
        commentService.postComment(request);
        return new BaseResponse(BaseResponseStatus.SUCCESS_WRITE);
    }
}
