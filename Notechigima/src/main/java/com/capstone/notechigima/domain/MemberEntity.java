package com.capstone.notechigima.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberEntity {
    private int userId;
    private int groupId;
    private char access;
}
