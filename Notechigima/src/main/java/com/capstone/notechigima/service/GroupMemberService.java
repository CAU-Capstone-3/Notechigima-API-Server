package com.capstone.notechigima.service;

import com.capstone.notechigima.domain.group_invite.GroupInvite;
import com.capstone.notechigima.domain.group_member.GroupAccessType;
import com.capstone.notechigima.domain.group_member.GroupMember;
import com.capstone.notechigima.repository.GroupInviteRepository;
import com.capstone.notechigima.repository.GroupMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupMemberService {

    private final GroupInviteRepository groupInviteRepository;
    private final GroupMemberRepository groupMemberRepository;

    public void joinMember(int groupInviteId) {
        GroupInvite groupInvite = groupInviteRepository.findById(groupInviteId).orElseThrow();
        GroupMember groupMember = GroupMember.builder()
                .studyGroup(groupInvite.getStudyGroup())
                .user(groupInvite.getUser())
                .access(GroupAccessType.WRITE)
                .build();
        groupMemberRepository.save(groupMember);
    }

}
