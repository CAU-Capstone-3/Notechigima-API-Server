package com.capstone.notechigima.controller;

import com.capstone.notechigima.config.BaseResponseStatus;
import com.capstone.notechigima.config.BaseResponse;
import com.capstone.notechigima.model.dto.topic.TopicResponseDTO;
import com.capstone.notechigima.service.TopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "topic", description = "주차별 조회 API")
@RestController
@RequestMapping("/api/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @ResponseBody
    @GetMapping("/subject/{subjectId}")
    @Operation(summary = "과목별 토픽 목록", description = "해당 과목 내의 모든 토픽 목록을 조회")
    public BaseResponse<List<TopicResponseDTO>> getTopicList(@PathVariable("subjectId") int subjectId) {
        return new BaseResponse(BaseResponseStatus.SUCCESS_READ, topicService.getTopicList(subjectId));
    }

    @ResponseBody
    @PostMapping("/advice/{topicId}")
    @Operation(summary = "분석 요청", description = "해당 토픽에 대한 분석 시작을 요청")
    public BaseResponse requestAnalysis(@PathVariable("topicId") int topicId) {
        topicService.requestAnalysis(topicId);
        return new BaseResponse(BaseResponseStatus.SUCCESS_EDIT);
    }

}
