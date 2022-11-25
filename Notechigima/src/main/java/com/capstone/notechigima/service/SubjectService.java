package com.capstone.notechigima.service;

import com.capstone.notechigima.dto.subject.SubjectGetResponseDTO;
import com.capstone.notechigima.mapper.SubjectMapper;
import com.capstone.notechigima.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public List<SubjectGetResponseDTO> getSubjectsByGroupId(int groupId) {
        return subjectRepository.findAllByStudyGroup_GroupId(groupId)
                .stream()
                .map(entity -> SubjectMapper.INSTANCE.toSubjectGetResponseDTO(entity))
                .collect(Collectors.toList());
    }


}
