package com.capstone.notechigima.service;

import com.capstone.notechigima.domain.group_invite.AcceptType;
import com.capstone.notechigima.domain.group_invite.GroupInvite;
import com.capstone.notechigima.domain.study_group.StudyGroup;
import com.capstone.notechigima.domain.users.User;
import com.capstone.notechigima.dto.invite.GroupInviteGetResponseDTO;
import com.capstone.notechigima.dto.invite.GroupInvitePostRequestDTO;
import com.capstone.notechigima.mapper.GroupInviteMapper;
import com.capstone.notechigima.repository.GroupInviteRepository;
import com.capstone.notechigima.repository.GroupRepository;
import com.capstone.notechigima.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GroupInviteService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final GroupInviteRepository groupInviteRepository;

    public int postGroupInvite(GroupInvitePostRequestDTO body) throws IllegalArgumentException, NoSuchElementException {
        User user = userRepository.findById(body.getUserId()).orElseThrow();
        StudyGroup group = groupRepository.findById(body.getGroupId()).orElseThrow();

        return groupInviteRepository.save(
                GroupInvite.builder()
                        .user(user)
                        .studyGroup(group)
                        .accepted(AcceptType.UNCHECKED)
                        .build()
        ).getGroupInviteId();
    }

    public List<GroupInviteGetResponseDTO> getGroupInvited(int userId) {
        return groupInviteRepository.findAllByUser_UserIdAndAccepted(userId, AcceptType.UNCHECKED).stream()
                .map(GroupInviteMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public void acceptInvite(int groupInviteId) throws IllegalArgumentException, NoSuchElementException{
        GroupInvite groupInvite = groupInviteRepository.findById(groupInviteId).orElseThrow();
        groupInvite.updateAccepted(AcceptType.ACCEPTED);
        groupInviteRepository.save(groupInvite);
    }

    public void declineInvite(int groupInviteId) throws IllegalArgumentException, NoSuchElementException{
        GroupInvite groupInvite = groupInviteRepository.findById(groupInviteId).orElseThrow();
        groupInvite.updateAccepted(AcceptType.DECLINED);
        groupInviteRepository.save(groupInvite);
    }
}
