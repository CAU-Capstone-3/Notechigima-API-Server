package com.capstone.notechigima.service;

import com.capstone.notechigima.config.ExceptionCode;
import com.capstone.notechigima.config.RestApiException;
import com.capstone.notechigima.domain.group_invite.AcceptType;
import com.capstone.notechigima.domain.group_invite.GroupInvite;
import com.capstone.notechigima.domain.study_group.StudyGroup;
import com.capstone.notechigima.domain.users.User;
import com.capstone.notechigima.dto.invite.GroupInviteReceivedGetResponseDTO;
import com.capstone.notechigima.dto.invite.GroupInvitePostRequestDTO;
import com.capstone.notechigima.dto.invite.GroupInviteSentGetResponseDTO;
import com.capstone.notechigima.mapper.GroupInviteMapper;
import com.capstone.notechigima.repository.GroupInviteRepository;
import com.capstone.notechigima.repository.GroupMemberRepository;
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
    private final GroupMemberRepository groupMemberRepository;
    private final GroupInviteRepository groupInviteRepository;

    public int postGroupInvite(GroupInvitePostRequestDTO body) throws RestApiException {
        User user;
        StudyGroup group;

        try {
            user = userRepository.getUserByEmail(body.getEmail()).orElseThrow(() -> new RestApiException(ExceptionCode.ERROR_NOT_FOUND_USER));
            group = groupRepository.findById(body.getGroupId()).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new RestApiException(ExceptionCode.ERROR_NOT_FOUND_RESOURCE);
        }

        validateDuplicationMember(group.getGroupId(), user.getUserId());
        validateDuplicationInvite(group.getGroupId(), user.getUserId());

        return groupInviteRepository.save(
                GroupInvite.builder()
                        .user(user)
                        .studyGroup(group)
                        .accepted(AcceptType.UNCHECKED)
                        .build()
        ).getGroupInviteId();
    }

    /*
    기존 그룹멤버 중복 초대 방지
     */
    private void validateDuplicationMember(int groupId, int userId) throws RestApiException {
        groupMemberRepository.findAllByStudyGroup_GroupIdAndUser_UserId(groupId, userId)
                .forEach(member -> {
                    if (member.getUser().getUserId() == userId) {
                        throw new RestApiException(ExceptionCode.ERROR_DUPLICATED_MEMBER);
                    }
                });
    }

    /*
    초대 요청 멤버 중복 초대 방지
     */
    private void validateDuplicationInvite(int groupId, int userId) throws RestApiException {
        groupInviteRepository.findAllByStudyGroup_GroupIdAndUser_UserId(groupId, userId)
                .forEach(invite -> {
                    if (invite.getAccepted() == AcceptType.ACCEPTED ||
                            invite.getAccepted() == AcceptType.UNCHECKED) {
                        throw new RestApiException(ExceptionCode.ERROR_DUPLICATED_INVITE);
                    }
                });
    }

    public List<GroupInviteReceivedGetResponseDTO> getGroupInvitedByUserId(int userId) {
        return groupInviteRepository.findAllByUser_UserIdAndAccepted(userId, AcceptType.UNCHECKED).stream()
                .map(GroupInviteMapper.INSTANCE::toReceivedDTO)
                .collect(Collectors.toList());
    }

    public List<GroupInviteSentGetResponseDTO> getUncheckedGroupInvitedByGroupId(int groupId) {
        return groupInviteRepository.findAllByStudyGroup_GroupIdAndAccepted(groupId, AcceptType.UNCHECKED).stream()
                .map(GroupInviteMapper.INSTANCE::toSentDTO)
                .collect(Collectors.toList());
    }

    public void acceptInvite(int groupInviteId) throws RestApiException {
        GroupInvite groupInvite = groupInviteRepository.findById(groupInviteId).orElseThrow(() -> {
            throw new RestApiException(ExceptionCode.ERROR_NOT_FOUND_RESOURCE);
        });
        groupInvite.updateAccepted(AcceptType.ACCEPTED);
        groupInviteRepository.save(groupInvite);
    }

    public void declineInvite(int groupInviteId) throws RestApiException {
        GroupInvite groupInvite = groupInviteRepository.findById(groupInviteId).orElseThrow(() -> {
            throw new RestApiException(ExceptionCode.ERROR_NOT_FOUND_RESOURCE);
        });
        groupInvite.updateAccepted(AcceptType.DECLINED);
        groupInviteRepository.save(groupInvite);
    }
}
