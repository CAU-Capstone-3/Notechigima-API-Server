package com.capstone.notechigima.dto.invite;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GroupInviteSentGetResponseDTO {
    @Schema(description = "사용자 ID", defaultValue = "2")
    private int userId;
    @Schema(description = "사용자명", defaultValue = "장훈석")
    private String nickname;

    @Builder
    public GroupInviteSentGetResponseDTO(int userId, String nickname) {
        this.userId = userId;
        this.nickname = nickname;
    }
}
