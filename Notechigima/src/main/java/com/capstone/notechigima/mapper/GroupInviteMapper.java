package com.capstone.notechigima.mapper;

import com.capstone.notechigima.config.ExceptionCode;
import com.capstone.notechigima.domain.group_invite.GroupInvite;
import com.capstone.notechigima.domain.group_member.GroupAccessType;
import com.capstone.notechigima.domain.users.User;
import com.capstone.notechigima.dto.invite.GroupInviteReceivedGetResponseDTO;
import com.capstone.notechigima.dto.invite.GroupInviteSentGetResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.NoSuchElementException;

@Mapper
public interface GroupInviteMapper {
    GroupInviteMapper INSTANCE = Mappers.getMapper(GroupInviteMapper.class);

    default GroupInviteReceivedGetResponseDTO toReceivedDTO(GroupInvite entity) {
        User groupOwner = entity
                .getStudyGroup()
                .getMembers()
                .stream()
                .filter(member -> member.getAccess() == GroupAccessType.OWNER)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(ExceptionCode.ERROR_NOT_FOUND_RESOURCE.getMessage()))
                .getUser();

        return GroupInviteReceivedGetResponseDTO.builder()
                .groupInviteId(entity.getGroupInviteId())
                .groupId(entity.getStudyGroup().getGroupId())
                .groupName(entity.getStudyGroup().getName())
                .groupOwnerId(groupOwner.getUserId())
                .groupOwnerName(groupOwner.getNickname())
                .build();
    }
    default GroupInviteSentGetResponseDTO toSentDTO(GroupInvite entity) {
        return GroupInviteSentGetResponseDTO.builder()
                .userId(entity.getUser().getUserId())
                .nickname(entity.getUser().getNickname())
                .build();
    }
}
