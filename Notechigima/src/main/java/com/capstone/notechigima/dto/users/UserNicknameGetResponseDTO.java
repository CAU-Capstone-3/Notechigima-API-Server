package com.capstone.notechigima.dto.users;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserNicknameGetResponseDTO {
    private int userId;
    private String userName;

    @Builder
    public UserNicknameGetResponseDTO(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
