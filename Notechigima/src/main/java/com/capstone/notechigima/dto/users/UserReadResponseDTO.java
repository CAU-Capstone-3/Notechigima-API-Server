package com.capstone.notechigima.dto.users;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserReadResponseDTO {
    @Schema(description = "유저 ID", defaultValue = "1")
    private int userId;
    @Schema(description = "이메일", defaultValue = "khk211113@naver.com")
    private String email;
    @Schema(description = "유저 이름", defaultValue = "김형기")
    private String nickname;

    @Builder
    public UserReadResponseDTO(int userId, String email, String nickname) {
        this.userId = userId;
        this.email = email;
        this.nickname = nickname;
    }
}
