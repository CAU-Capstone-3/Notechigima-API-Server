package com.capstone.notechigima.service;

import com.capstone.notechigima.domain.study_group.StudyGroup;
import com.capstone.notechigima.dto.subject.SubjectGetResponseDTO;
import com.capstone.notechigima.dto.subject.SubjectPostRequestDTO;
import com.capstone.notechigima.mapper.SubjectMapper;
import com.capstone.notechigima.repository.GroupRepository;
import com.capstone.notechigima.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final GroupRepository groupRepository;
    private final SubjectRepository subjectRepository;

    public List<SubjectGetResponseDTO> getSubjectsByGroupId(int groupId) {
        return subjectRepository.findAllByStudyGroup_GroupId(groupId)
                .stream()
                .map(entity -> SubjectMapper.INSTANCE.toSubjectGetResponseDTO(entity))
                .collect(Collectors.toList());
    }

    public void postSubject(SubjectPostRequestDTO body) {
        StudyGroup studyGroup = groupRepository.findById(body.getGroupId()).orElseThrow();
        subjectRepository.save(SubjectMapper.INSTANCE.toEntity(body, studyGroup));
    }

}
