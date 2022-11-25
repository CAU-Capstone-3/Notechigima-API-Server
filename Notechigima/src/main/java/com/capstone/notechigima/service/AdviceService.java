package com.capstone.notechigima.service;

import com.capstone.notechigima.dto.advice.AdviceGetResponseDTO;

import java.util.List;

public interface AdviceService {

    List<AdviceGetResponseDTO> getAdviceList(int sectionId);

}
