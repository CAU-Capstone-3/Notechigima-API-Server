package com.capstone.notechigima.service;

import com.capstone.notechigima.config.ExceptionCode;
import com.capstone.notechigima.config.RestApiException;
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

    public void joinMember(int groupInviteId) throws RestApiException  {
        GroupInvite groupInvite = groupInviteRepository.findById(groupInviteId).orElseThrow(() -> {
            throw new RestApiException(ExceptionCode.ERROR_NOT_FOUND_RESOURCE);
        });
        GroupMember groupMember = GroupMember.builder()
                .studyGroup(groupInvite.getStudyGroup())
                .user(groupInvite.getUser())
                .access(GroupAccessType.WRITE)
                .build();
        groupMemberRepository.save(groupMember);
    }

}
