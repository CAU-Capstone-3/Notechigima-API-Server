package com.capstone.notechigima.service;

import com.capstone.notechigima.model.advice.AdviceResponseDTO;

import java.util.List;

public interface AdviceService {

    List<AdviceResponseDTO> getAdviceList(int sectionId);

}
