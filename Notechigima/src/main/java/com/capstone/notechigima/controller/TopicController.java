package com.capstone.notechigima.controller;

import com.capstone.notechigima.config.ExceptionCode;
import com.capstone.notechigima.config.SuccessCode;
import com.capstone.notechigima.config.BaseResponse;
import com.capstone.notechigima.domain.topic.TopicAnalyzedType;
import com.capstone.notechigima.dto.advice.AdviceGetResponseDTO;
import com.capstone.notechigima.dto.note.NoteListGetResponseDTO;
import com.capstone.notechigima.dto.topic.TopicWithNoteListGetResponseDTO;
import com.capstone.notechigima.dto.users.UserNicknameGetResponseDTO;
import com.capstone.notechigima.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.AccessException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.capstone.notechigima.config.jwt.JwtUtils.ACCESS_TOKEN_HEADER;

@Tag(name = "topic", description = "토픽 관련 API")
@RestController
@RequestMapping("/api/topics")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;
    private final NoteService noteService;
    private final AdviceService adviceService;
    private final AuthService authService;

    @ResponseBody
    @GetMapping("/{topicId}/notes")
    @Operation(summary = "토픽별 노트 목록", description = "해당 토픽 내에서 작성된 노트의 목록")
    public BaseResponse<TopicWithNoteListGetResponseDTO> getNoteList(
            HttpServletRequest request,
            @PathVariable("topicId") int topicId
    ) throws AccessException {

        authService.authorizationByTopicId(request.getHeader(ACCESS_TOKEN_HEADER), topicId);

        List<NoteListGetResponseDTO> notes = noteService.getNoteList(topicId);
        List<UserNicknameGetResponseDTO> unwrittenUsers = topicService.getUnwrittenUsers(topicId);
        String topicName = topicService.getTopic(topicId).getTitle();

        TopicWithNoteListGetResponseDTO response = TopicWithNoteListGetResponseDTO.builder()
                .topicId(topicId)
                .topicName(topicName)
                .notes(notes)
                .unwrittenUsers(unwrittenUsers)
                .build();
        return new BaseResponse(SuccessCode.SUCCESS_READ, response);
    }

    @ResponseBody
    @PostMapping("/{topicId}/advices")
    @Operation(summary = "분석 요청", description = "해당 토픽에 대한 분석 시작을 요청")
    public BaseResponse requestAnalysis(
            HttpServletRequest request,
            @PathVariable("topicId") int topicId
            ) throws AccessException {

        authService.authorizationByTopicId(request.getHeader(ACCESS_TOKEN_HEADER), topicId);

        if (topicService.getTopic(topicId).getAnalyzed() != TopicAnalyzedType.READY)
            return new BaseResponse(ExceptionCode.ERROR_INVALID_ANALYZED_STATUS);
        topicService.requestAnalysis(topicId);
        return new BaseResponse(SuccessCode.SUCCESS_WRITE);
    }

    @ResponseBody
    @GetMapping("/{topicId}/advices")
    @Operation(summary = "토픽별 분석결과 API", description = "토픽별 분석결과 목록을 조회하는 API 입니다.")
    public BaseResponse<List<AdviceGetResponseDTO>> getAdviceList(
            HttpServletRequest request,
            @PathVariable("topicId") int topicId
    ) throws AccessException {

        authService.authorizationByTopicId(request.getHeader(ACCESS_TOKEN_HEADER), topicId);

        return new BaseResponse(SuccessCode.SUCCESS_READ, adviceService.getAdviceList(topicId));
    }
}
