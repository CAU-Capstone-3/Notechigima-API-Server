package com.capstone.notechigima.controller;

import com.capstone.notechigima.config.BaseResponse;
import com.capstone.notechigima.config.SuccessCode;
import com.capstone.notechigima.dto.comment.CommentPostReqeustDTO;
import com.capstone.notechigima.service.AdviceService;
import com.capstone.notechigima.service.AuthService;
import com.capstone.notechigima.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.AccessException;
import org.springframework.web.bind.annotation.*;

import static com.capstone.notechigima.config.jwt.JwtUtils.ACCESS_TOKEN_HEADER;

@Tag(name = "advice", description = "분석 결과 API")
@RestController
@RequestMapping("/api/advices")
@RequiredArgsConstructor
public class AdviceController {

    private final AdviceService adviceService;
    private final CommentService commentService;
    private final AuthService authService;


    @PostMapping("/{adviceId}/comments")
    @Operation(summary = "댓글 작성", description = "분석 결과에 댓글 작성")
    public BaseResponse postComment(
            @PathVariable("adviceId") int adviceId,
            @RequestHeader(ACCESS_TOKEN_HEADER) String token,
            @RequestBody CommentPostReqeustDTO request) throws AccessException {

        authService.validateByAdviceId(token, adviceId);

        commentService.postComment(adviceId, request);
        return new BaseResponse(SuccessCode.SUCCESS_WRITE);
    }

}
