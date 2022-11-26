package com.capstone.notechigima.service;

import com.capstone.notechigima.domain.group_invite.AcceptType;
import com.capstone.notechigima.domain.group_invite.GroupInvite;
import com.capstone.notechigima.domain.study_group.StudyGroup;
import com.capstone.notechigima.domain.users.User;
import com.capstone.notechigima.dto.invite.GroupInviteGetResponseDTO;
import com.capstone.notechigima.mapper.GroupInviteMapper;
import com.capstone.notechigima.repository.GroupInviteRepository;
import com.capstone.notechigima.repository.GroupRepository;
import com.capstone.notechigima.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GroupInviteService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final GroupInviteRepository groupInviteRepository;

    public int postGroupInvite(int groupId, int userId) {
        User user = userRepository.findById(userId).orElseThrow();
        StudyGroup group = groupRepository.findById(groupId).orElseThrow();

        return groupInviteRepository.save(
                GroupInvite.builder()
                        .user(user)
                        .studyGroup(group)
                        .accepted(AcceptType.UNCHECKED)
                        .build()
        ).getGroupInviteId();
    }

    public List<GroupInviteGetResponseDTO> getGroupInvited(int userId) {
        return groupInviteRepository.findAllByUser_UserId(userId).stream()
                .map(entity -> GroupInviteMapper.INSTANCE.toDto(entity))
                .collect(Collectors.toList());
    }
}
