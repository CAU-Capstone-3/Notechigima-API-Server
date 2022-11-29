package com.capstone.notechigima.mapper;

import com.capstone.notechigima.domain.group_invite.GroupInvite;
import com.capstone.notechigima.dto.invite.GroupInviteReceivedGetResponseDTO;
import com.capstone.notechigima.dto.invite.GroupInviteSentGetResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GroupInviteMapper {
    GroupInviteMapper INSTANCE = Mappers.getMapper(GroupInviteMapper.class);

    GroupInviteReceivedGetResponseDTO toReceivedDTO(GroupInvite entity);
    GroupInviteSentGetResponseDTO toSentDTO(GroupInvite entity);
}
