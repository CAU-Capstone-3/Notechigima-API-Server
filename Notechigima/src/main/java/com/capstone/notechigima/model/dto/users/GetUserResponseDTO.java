package com.capstone.notechigima.model.dto.users;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetUserResponseDTO {
    private int userId;
    private String email;
    private String nickname;
}
