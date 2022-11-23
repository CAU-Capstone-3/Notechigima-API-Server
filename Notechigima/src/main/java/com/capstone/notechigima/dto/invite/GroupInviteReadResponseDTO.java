package com.capstone.notechigima.dto.invite;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GroupInviteReadResponseDTO {
    @Schema(description = "그룹 초대 ID", defaultValue = "14")
    private int groupInviteId;
    @Schema(description = "그룹 ID", defaultValue = "1")
    private int groupId;
    @Schema(description = "그룹명", defaultValue = "장찬기 스터디")
    private String groupName;
    @Schema(description = "그룹장 ID", defaultValue = "3")
    private int groupOwnerId;
    @Schema(description = "그룹장 이름", defaultValue = "장훈석")
    private String groupOwnerName;

    @Builder
    public GroupInviteReadResponseDTO(int groupInviteId, int groupId, String groupName, int groupOwnerId, String groupOwnerName) {
        this.groupInviteId = groupInviteId;
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupOwnerId = groupOwnerId;
        this.groupOwnerName = groupOwnerName;
    }

    
}
