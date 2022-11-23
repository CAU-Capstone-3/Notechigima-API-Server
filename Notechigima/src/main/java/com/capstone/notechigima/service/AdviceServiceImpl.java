package com.capstone.notechigima.service;

import com.capstone.notechigima.model.ModelMapper;
import com.capstone.notechigima.model.advice.AdviceResponseDTO;
import com.capstone.notechigima.repository.AdviceRepository;
import com.capstone.notechigima.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdviceServiceImpl implements AdviceService {

    private final AdviceRepository adviceRepository;
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    public AdviceServiceImpl(AdviceRepository adviceRepository, CommentRepository commentRepository, ModelMapper modelMapper) {
        this.adviceRepository = adviceRepository;
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AdviceResponseDTO> getAdviceList(int topicId) {
        return adviceRepository.getAdviceList(topicId).stream()
                .map(entity -> modelMapper.map(entity)
                ).collect(Collectors.toList());
    }
}
