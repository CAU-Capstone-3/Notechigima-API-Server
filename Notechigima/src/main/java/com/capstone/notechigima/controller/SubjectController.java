package com.capstone.notechigima.controller;

import com.capstone.notechigima.config.BaseResponse;
import com.capstone.notechigima.config.SuccessCode;
import com.capstone.notechigima.dto.subject.SubjectPostRequestDTO;
import com.capstone.notechigima.dto.subject.SubjectWithTopicsGetResponseDTO;
import com.capstone.notechigima.dto.topic.TopicGetResponseDTO;
import com.capstone.notechigima.service.AuthService;
import com.capstone.notechigima.service.SubjectService;
import com.capstone.notechigima.service.TopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.AccessException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.capstone.notechigima.config.jwt.JwtUtils.ACCESS_TOKEN_HEADER;

@Tag(name = "subject", description = "과목 API")
@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final TopicService topicService;
    private final SubjectService subjectService;
    private final AuthService authService;

    @ResponseBody
    @GetMapping("/{subjectId}/topics")
    @Operation(summary = "과목별 토픽 목록", description = "해당 과목 내의 모든 토픽 목록을 조회")
    public BaseResponse<SubjectWithTopicsGetResponseDTO> getTopicList(
            HttpServletRequest request,
            @PathVariable("subjectId") int subjectId
    ) throws AccessException {

        authService.authorizationBySubjectId(request.getHeader(ACCESS_TOKEN_HEADER), subjectId);
        return new BaseResponse(SuccessCode.SUCCESS_READ, topicService.getTopicListWithSubject(subjectId));
    }

    @PostMapping
    @Operation(summary = "과목 생성", description = "해당 그룹 내에 과목 생성")
    public BaseResponse postSubject(
            HttpServletRequest request,
            @RequestBody SubjectPostRequestDTO body
    ) throws AccessException {

        authService.authorizationByGroupId(request.getHeader(ACCESS_TOKEN_HEADER), body.getGroupId());

        subjectService.postSubject(body);
        return new BaseResponse(SuccessCode.SUCCESS_WRITE);
    }

}
