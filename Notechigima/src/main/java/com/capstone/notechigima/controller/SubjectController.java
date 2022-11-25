package com.capstone.notechigima.controller;

import com.capstone.notechigima.config.BaseResponse;
import com.capstone.notechigima.config.BaseResponseStatus;
import com.capstone.notechigima.dto.topic.TopicResponseDTO;
import com.capstone.notechigima.service.TopicServiceJPA;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "subject", description = "과목 API")
@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    private final TopicServiceJPA topicService;

    public SubjectController(TopicServiceJPA topicService) {
        this.topicService = topicService;
    }

    @ResponseBody
    @GetMapping("/{subjectId}/topic")
    @Operation(summary = "과목별 토픽 목록", description = "해당 과목 내의 모든 토픽 목록을 조회")
    public BaseResponse<List<TopicResponseDTO>> getTopicList(@PathVariable("subjectId") int subjectId) {
        return new BaseResponse(BaseResponseStatus.SUCCESS_READ, topicService.getTopicList(subjectId));
    }

}
