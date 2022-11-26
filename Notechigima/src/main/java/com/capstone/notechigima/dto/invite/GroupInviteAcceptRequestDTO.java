package com.capstone.notechigima.dto.invite;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GroupInviteAcceptRequestDTO {

    @Schema(description = "그룹 초대 ID", defaultValue = "13")
    private int groupInviteId;

    @Schema(description = "초대 허락 여부 (수락 = true, 거절 = false)", defaultValue = "true")
    private boolean accept;

    @Builder
    public GroupInviteAcceptRequestDTO(int groupInviteId, boolean accept) {
        this.groupInviteId = groupInviteId;
        this.accept = accept;
    }
}
