package com.capstone.notechigima.controller;

import com.capstone.notechigima.config.BaseResponse;
import com.capstone.notechigima.config.BaseResponseStatus;
import com.capstone.notechigima.model.dto.advice.AdviceResponseDTO;
import com.capstone.notechigima.service.AdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/advice")
public class AdviceController {

    @Autowired
    private final AdviceService adviceService;


    public AdviceController(AdviceService adviceService) {
        this.adviceService = adviceService;
    }

    @ResponseBody
    @GetMapping("/{sectionId}")
    public BaseResponse<List<AdviceResponseDTO>> getAdviceList(@PathVariable("sectionId") int sectionId) {
        return new BaseResponse(BaseResponseStatus.SUCCESS_READ, adviceService.getAdviceList(sectionId));
    }

}
