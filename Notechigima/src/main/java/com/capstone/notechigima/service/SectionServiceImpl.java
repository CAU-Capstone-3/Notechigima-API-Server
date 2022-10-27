package com.capstone.notechigima.service;

import com.capstone.notechigima.model.ModelMapper;
import com.capstone.notechigima.model.dto.section.SectionResponseDTO;
import com.capstone.notechigima.repository.SectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;

    private final ModelMapper modelMapper;

    public SectionServiceImpl(SectionRepository sectionRepository, ModelMapper modelMapper) {
        this.sectionRepository = sectionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<SectionResponseDTO> getSectionList(int subjectId) {
        return sectionRepository.getSectionList(subjectId).stream()
                .map(entity -> modelMapper.map(entity)
                ).collect(Collectors.toList());
    }
}
