package com.capstone.notechigima.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserEntity {
    private int userId;
    private String email;
    private String nickname;
}
