package com.capstone.notechigima.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginPostRequestDTO {
    @Schema(description = "로그인할 아이디", defaultValue = "khk211113@naver.com")
    private String email;
    @Schema(description = "비밀번호", defaultValue = "123456")
    private String password;

    @Builder
    public LoginPostRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
