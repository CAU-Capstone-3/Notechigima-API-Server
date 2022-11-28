package com.capstone.notechigima.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupPostRequestDTO {
    @Schema(description = "아이디로 사용할 이메일", defaultValue = "khk211113@naver.com")
    private String email;
    @Schema(description = "비밀번호", defaultValue = "123456")
    private String password;
    @Schema(description = "사용자 닉네임", defaultValue = "김형기")
    private String nickname;

    @Builder
    public SignupPostRequestDTO(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }
}
