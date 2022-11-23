package com.capstone.notechigima.controller;

import com.capstone.notechigima.config.BaseException;
import com.capstone.notechigima.config.BaseResponseStatus;
import com.capstone.notechigima.config.BaseResponse;
import com.capstone.notechigima.dto.advice.AdviceResponseDTO;
import com.capstone.notechigima.dto.note.GetNoteSummarizedDTO;
import com.capstone.notechigima.dto.note.PostNoteRequestDTO;
import com.capstone.notechigima.service.AdviceService;
import com.capstone.notechigima.service.NoteService;
import com.capstone.notechigima.service.TopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "topic", description = "토푁 관련 API")
@RestController
@RequestMapping("/api/topic")
public class TopicController {

    private final TopicService topicService;
    private final NoteService noteService;
    private final AdviceService adviceService;

    public TopicController(TopicService topicService, NoteService noteService, AdviceService adviceService) {
        this.topicService = topicService;
        this.noteService = noteService;
        this.adviceService = adviceService;
    }

    @ResponseBody
    @GetMapping("/{topicId}/note")
    @Operation(summary = "토픽별 노트 목록", description = "해당 토픽 내에서 작성된 노트의 목록")
    public BaseResponse<List<GetNoteSummarizedDTO>> getNoteList(@PathVariable("topicId") int topicId) throws BaseException {
        return new BaseResponse(BaseResponseStatus.SUCCESS_READ, noteService.getNoteList(topicId));
    }

    @ResponseBody
    @PostMapping("/{topicId}/advice")
    @Operation(summary = "분석 요청", description = "해당 토픽에 대한 분석 시작을 요청")
    public BaseResponse requestAnalysis(@PathVariable("topicId") int topicId) {
        if (topicService.getTopic(topicId).getAnalyzed() != 'B')
            return new BaseResponse(BaseResponseStatus.CAN_NOT_ANALYZE);
        topicService.requestAnalysis(topicId);
        return new BaseResponse(BaseResponseStatus.SUCCESS_WRITE);
    }

    @ResponseBody
    @GetMapping("/{topicId}/advice")
    @Operation(summary = "토픽별 분석결과 API", description = "토픽별 분석결과 목록을 조회하는 API 입니다.")
    public BaseResponse<List<AdviceResponseDTO>> getAdviceList(@PathVariable("topicId") int topicId) {
        return new BaseResponse(BaseResponseStatus.SUCCESS_READ, adviceService.getAdviceList(topicId));
    }
}
