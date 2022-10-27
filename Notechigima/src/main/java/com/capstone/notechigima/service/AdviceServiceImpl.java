package com.capstone.notechigima.service;

import com.capstone.notechigima.model.ModelMapper;
import com.capstone.notechigima.model.dto.advice.AdviceResponseDTO;
import com.capstone.notechigima.repository.AdviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdviceServiceImpl implements AdviceService {

    @Autowired
    private final AdviceRepository adviceRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public AdviceServiceImpl(AdviceRepository adviceRepository, ModelMapper modelMapper) {
        this.adviceRepository = adviceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AdviceResponseDTO> getAdviceList(int sectionId) {
        return adviceRepository.getAdviceList(sectionId).stream()
                .map(entity -> modelMapper.map(entity)
                ).collect(Collectors.toList());
    }
}
