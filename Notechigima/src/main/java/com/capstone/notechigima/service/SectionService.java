package com.capstone.notechigima.service;

import com.capstone.notechigima.model.dto.section.SectionResponseDTO;

import java.util.List;

public interface SectionService {

    List<SectionResponseDTO> getSectionList(int subjectId);

    int requestAnalysis(int sectionId);

}
