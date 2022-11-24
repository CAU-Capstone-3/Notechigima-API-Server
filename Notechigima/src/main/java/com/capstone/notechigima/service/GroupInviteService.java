package com.capstone.notechigima.service;

import com.capstone.notechigima.domain.group_invite.GroupInvite;
import com.capstone.notechigima.repository.GroupInviteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GroupInviteService {
    private final GroupInviteRepository groupInviteRepository;

    public int save(int groupId, int userId) {
        return groupInviteRepository.save(
                GroupInvite.builder()
                .groupId(groupId)
                .userId(userId)
                .build()
        ).getGroupInviteId();
    }
}
