package com.capstone.notechigima.model.dto.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AdviceInferenceRequestVO {
    private List<String> docs;
    private String sentence;
}
