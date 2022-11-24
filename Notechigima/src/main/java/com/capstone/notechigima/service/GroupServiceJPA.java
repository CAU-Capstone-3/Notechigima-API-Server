package com.capstone.notechigima.service;

import com.capstone.notechigima.domain.ActiveStatus;
import com.capstone.notechigima.domain.group_member.GroupAccessType;
import com.capstone.notechigima.domain.group_member.GroupMember;
import com.capstone.notechigima.domain.study_group.StudyGroup;
import com.capstone.notechigima.domain.users.User;
import com.capstone.notechigima.dto.ModelMapper;
import com.capstone.notechigima.dto.study_group.StudyGroupGetResponseDTO;
import com.capstone.notechigima.dto.study_group.StudyGroupPostRequestDTO;
import com.capstone.notechigima.repository.GroupMemberRepository;
import com.capstone.notechigima.repository.GroupRepository;
import com.capstone.notechigima.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GroupServiceJPA {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final ModelMapper modelMapper;

    public List<StudyGroupGetResponseDTO> getGroups(int userId) {
        return groupMemberRepository.findAllByUser_UserId(userId).stream()
                .map(entity -> modelMapper.map(entity.getStudyGroup()))
                .collect(Collectors.toList());
    }

    public int postStudyGroup(StudyGroupPostRequestDTO body) {
        StudyGroup createStudyGroup = StudyGroup.builder()
                .name(body.getGroupName())
                .status(ActiveStatus.ACTIVE)
                .build();

        groupRepository.save(createStudyGroup);
        User user = userRepository.getReferenceById(body.getUserId());

        groupMemberRepository.save(
                GroupMember.builder()
                        .studyGroup(createStudyGroup)
                        .user(user)
                        .access(GroupAccessType.OWNER)
                        .build());

        return createStudyGroup.getGroupId();
    }
}
