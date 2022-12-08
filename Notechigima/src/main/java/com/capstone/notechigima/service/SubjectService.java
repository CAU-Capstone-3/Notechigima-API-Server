package com.capstone.notechigima.service;

import com.capstone.notechigima.config.ExceptionCode;
import com.capstone.notechigima.config.RestApiException;
import com.capstone.notechigima.domain.group_member.GroupAccessType;
import com.capstone.notechigima.domain.group_member.GroupMember;
import com.capstone.notechigima.domain.study_group.StudyGroup;
import com.capstone.notechigima.domain.users.User;
import com.capstone.notechigima.dto.study_group.StudyGroupWithSubjectsGetResponseDTO;
import com.capstone.notechigima.dto.subject.SubjectGetResponseDTO;
import com.capstone.notechigima.dto.subject.SubjectPostRequestDTO;
import com.capstone.notechigima.mapper.SubjectMapper;
import com.capstone.notechigima.repository.GroupRepository;
import com.capstone.notechigima.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final GroupRepository groupRepository;
    private final SubjectRepository subjectRepository;

    public StudyGroupWithSubjectsGetResponseDTO getSubjectsByGroupIdWithGroup(int groupId) throws RestApiException{
        StudyGroup studyGroup = groupRepository.findById(groupId)
                .orElseThrow(() -> new NoSuchElementException(ExceptionCode.ERROR_NOT_FOUND_RESOURCE.getMessage()));

        List<SubjectGetResponseDTO> subjects = subjectRepository.findAllByStudyGroup_GroupId(groupId)
                .stream()
                .map(SubjectMapper.INSTANCE::toSubjectGetResponseDTO).toList();

        User owner = studyGroup.getMembers().stream()
                .filter(member -> member.getAccess() == GroupAccessType.OWNER)
                .findFirst()
                .map(GroupMember::getUser)
                .orElseThrow(() -> {
                    throw new RestApiException(ExceptionCode.ERROR_NOT_FOUND_USER);
                });

        return StudyGroupWithSubjectsGetResponseDTO.builder()
                .groupId(groupId)
                .groupName(studyGroup.getName())
                .groupOwnerId(owner.getUserId())
                .groupOwnerName(owner.getNickname())
                .subjects(subjects)
                .build();
    }

    public void postSubject(SubjectPostRequestDTO body) throws RestApiException {
        StudyGroup studyGroup = groupRepository.findById(body.getGroupId()).orElseThrow(() -> {
            throw new RestApiException(ExceptionCode.ERROR_NOT_FOUND_RESOURCE);
        });
        subjectRepository.save(SubjectMapper.INSTANCE.toEntity(body, studyGroup));
    }

}
