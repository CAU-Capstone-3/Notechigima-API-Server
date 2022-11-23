package com.capstone.notechigima.model.dao.group;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GroupCreateEntity {
    private int ownerId;
    private String groupName;
}
