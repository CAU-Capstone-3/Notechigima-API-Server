package com.capstone.notechigima.controller;

import com.capstone.notechigima.config.BaseResponse;
import com.capstone.notechigima.config.BaseResponseStatus;
import com.capstone.notechigima.model.dto.advice.AdviceResponseDTO;
import com.capstone.notechigima.service.AdviceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "advice", description = "분석 결과 API")
@RestController
@RequestMapping("/api/advice")
public class AdviceController {

    @Autowired
    private final AdviceService adviceService;


    public AdviceController(AdviceService adviceService) {
        this.adviceService = adviceService;
    }

    @ResponseBody
    @GetMapping("/{topicId}")
    @Operation(summary = "토픽별 분석결과 API", description = "토픽별 분석결과 목록을 조회하는 API 입니다.")
    public BaseResponse<List<AdviceResponseDTO>> getAdviceList(@PathVariable("topicId") int topicId) {
        return new BaseResponse(BaseResponseStatus.SUCCESS_READ, adviceService.getAdviceList(topicId));
    }

}
