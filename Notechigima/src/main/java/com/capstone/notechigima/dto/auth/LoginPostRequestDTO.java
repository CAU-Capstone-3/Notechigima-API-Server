package com.capstone.notechigima.dto.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginPostRequestDTO {
    private String email;
    private String password;

    @Builder
    public LoginPostRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
