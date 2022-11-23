package com.capstone.notechigima.controller;

import com.capstone.notechigima.config.BaseException;
import com.capstone.notechigima.config.BaseResponse;
import com.capstone.notechigima.config.BaseResponseStatus;
import com.capstone.notechigima.dto.comment.PostCommentRequestDTO;
import com.capstone.notechigima.service.AdviceService;
import com.capstone.notechigima.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "advice", description = "분석 결과 API")
@RestController
@RequestMapping("/api/advice")
public class AdviceController {

    private final AdviceService adviceService;
    private final CommentService commentService;


    public AdviceController(AdviceService adviceService, CommentService commentService) {
        this.adviceService = adviceService;
        this.commentService = commentService;
    }

    @PostMapping("/{adviceId}/comment")
    @Operation(summary = "댓글 작성", description = "분석 결과에 댓글 작성")
    public BaseResponse postComment(@PathVariable("adviceId") int adviceId, @RequestBody PostCommentRequestDTO request) throws BaseException {
        commentService.postComment(adviceId, request);
        return new BaseResponse(BaseResponseStatus.SUCCESS_WRITE);
    }

}
