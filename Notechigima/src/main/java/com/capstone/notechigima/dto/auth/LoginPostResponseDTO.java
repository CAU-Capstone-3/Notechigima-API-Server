package com.capstone.notechigima.dto.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginPostResponseDTO {
    private int userId;
    private String accessToken;

    @Builder
    public LoginPostResponseDTO(int userId, String accessToken) {
        this.userId = userId;
        this.accessToken = accessToken;
    }
}
