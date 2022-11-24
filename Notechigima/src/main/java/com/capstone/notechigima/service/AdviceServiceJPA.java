package com.capstone.notechigima.service;

import com.capstone.notechigima.dto.advice.AdviceGetResponseDTO;
import com.capstone.notechigima.mapper.AdviceMapper;
import com.capstone.notechigima.repository.AdviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdviceServiceJPA {

    private final AdviceRepository adviceRepository;

    public List<AdviceGetResponseDTO> getAdviceList(int topicId) {
        return adviceRepository.findAllBySentence1_Note_Topic_TopicId(topicId).stream()
                .map(entity -> AdviceMapper.INSTANCE.toAdviceGetResponseDTO(entity)
                ).collect(Collectors.toList());
    }
}
