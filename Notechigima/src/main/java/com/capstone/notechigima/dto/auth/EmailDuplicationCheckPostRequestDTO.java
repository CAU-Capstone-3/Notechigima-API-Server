package com.capstone.notechigima.dto.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailDuplicationCheckPostRequestDTO {
    private String email;

    @Builder
    public EmailDuplicationCheckPostRequestDTO(String email) {
        this.email = email;
    }
}
