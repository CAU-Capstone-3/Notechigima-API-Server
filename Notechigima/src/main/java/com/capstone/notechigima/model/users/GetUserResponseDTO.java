package com.capstone.notechigima.model.users;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetUserResponseDTO {
    @Schema(description = "유저 ID", defaultValue = "1")
    private int userId;
    @Schema(description = "이메일", defaultValue = "khk211113@naver.com")
    private String email;
    @Schema(description = "유저 이름", defaultValue = "김형기")
    private String nickname;
}
