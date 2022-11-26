package com.capstone.notechigima.dto.invite;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GroupInvitePostRequestDTO {
    @Schema(description = "초대하는 그룹 ID", defaultValue = "1")
    private int groupId;
    @Schema(description = "초대할 사용자 ID", defaultValue = "3")
    private int userId;

    @Builder
    public GroupInvitePostRequestDTO(int groupId, int userId) {
        this.groupId = groupId;
        this.userId = userId;
    }
}
