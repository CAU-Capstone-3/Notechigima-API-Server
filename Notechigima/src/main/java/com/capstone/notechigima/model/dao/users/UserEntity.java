package com.capstone.notechigima.model.dao.users;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserEntity {
    private int userId;
    private String email;
    private String nickname;
}
