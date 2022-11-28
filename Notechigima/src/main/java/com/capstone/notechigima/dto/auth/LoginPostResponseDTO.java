package com.capstone.notechigima.dto.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginPostResponseDTO {
    private String accessToken;

    @Builder
    public LoginPostResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }
}
