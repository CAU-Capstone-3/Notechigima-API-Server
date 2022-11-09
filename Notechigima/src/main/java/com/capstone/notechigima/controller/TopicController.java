package com.capstone.notechigima.controller;

import com.capstone.notechigima.config.BaseResponseStatus;
import com.capstone.notechigima.config.BaseResponse;
import com.capstone.notechigima.model.dto.topic.TopicResponseDTO;
import com.capstone.notechigima.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @ResponseBody
    @GetMapping("/subject/{subjectId}")
    public BaseResponse<List<TopicResponseDTO>> getSectionList(@PathVariable("subjectId") int subjectId) {
        return new BaseResponse(BaseResponseStatus.SUCCESS_READ, topicService.getSectionList(subjectId));
    }

    @ResponseBody
    @PostMapping("/advice/{topicId}")
    public BaseResponse requestAnalysis(@PathVariable("topicId") int topicId) {
        topicService.requestAnalysis(topicId);
        return new BaseResponse(BaseResponseStatus.SUCCESS_EDIT);
    }

}
