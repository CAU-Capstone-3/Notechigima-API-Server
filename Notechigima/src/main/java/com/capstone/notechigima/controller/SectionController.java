package com.capstone.notechigima.controller;

import com.capstone.notechigima.config.BaseResponseStatus;
import com.capstone.notechigima.config.BaseResponse;
import com.capstone.notechigima.model.dto.section.SectionResponseDTO;
import com.capstone.notechigima.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/section")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @ResponseBody
    @GetMapping("/subject/{subjectId}")
    public BaseResponse<List<SectionResponseDTO>> getSectionList(@PathVariable("subjectId") int subjectId) {
        return new BaseResponse(BaseResponseStatus.SUCCESS_READ, sectionService.getSectionList(subjectId));
    }

    @ResponseBody
    @PostMapping("/advice/{sectionId}")
    public BaseResponse requestAnalysis(@PathVariable("sectionId") int sectionId) {
        sectionService.requestAnalysis(sectionId);
        return new BaseResponse(BaseResponseStatus.SUCCESS_EDIT);
    }

}
