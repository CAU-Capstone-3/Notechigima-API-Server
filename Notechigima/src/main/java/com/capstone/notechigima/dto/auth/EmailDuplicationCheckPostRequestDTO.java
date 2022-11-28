package com.capstone.notechigima.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailDuplicationCheckPostRequestDTO {
    @Schema(description = "중복확인할 이메일", defaultValue = "khk211113@naver.com")
    private String email;

    @Builder
    public EmailDuplicationCheckPostRequestDTO(String email) {
        this.email = email;
    }
}
