package com.capstone.notechigima.model.dao.group;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberEntity {
    private int userId;
    private int groupId;
    private char access;
}
