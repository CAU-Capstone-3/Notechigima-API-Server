package com.capstone.notechigima.service;

import com.capstone.notechigima.config.ExceptionCode;
import com.capstone.notechigima.config.RestApiException;
import com.capstone.notechigima.domain.ActiveStatus;
import com.capstone.notechigima.domain.group_member.GroupAccessType;
import com.capstone.notechigima.domain.group_member.GroupMember;
import com.capstone.notechigima.domain.study_group.StudyGroup;
import com.capstone.notechigima.domain.users.User;
import com.capstone.notechigima.dto.study_group.StudyGroupGetResponseDTO;
import com.capstone.notechigima.dto.study_group.StudyGroupPostRequestDTO;
import com.capstone.notechigima.mapper.StudyGroupMapper;
import com.capstone.notechigima.repository.GroupMemberRepository;
import com.capstone.notechigima.repository.GroupRepository;
import com.capstone.notechigima.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GroupService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;

    public List<StudyGroupGetResponseDTO> getStudyGroupsByUserId(int userId) {
        return groupMemberRepository.findAllByUser_UserId(userId).stream()
                .map(StudyGroupMapper.INSTANCE::toStudyGroupGetResponseDTO)
                .collect(Collectors.toList());
    }

    public int postStudyGroup(StudyGroupPostRequestDTO body) throws RestApiException {
        User user = userRepository.findById(body.getUserId()).orElseThrow(() -> {
            throw new RestApiException(ExceptionCode.ERROR_NOT_FOUND_USER);
        });

        StudyGroup createStudyGroup = StudyGroup.builder()
                .name(body.getGroupName())
                .status(ActiveStatus.ACTIVE)
                .build();

        groupRepository.save(createStudyGroup);

        groupMemberRepository.save(
                GroupMember.builder()
                        .studyGroup(createStudyGroup)
                        .user(user)
                        .access(GroupAccessType.OWNER)
                        .build());

        return createStudyGroup.getGroupId();
    }
}
