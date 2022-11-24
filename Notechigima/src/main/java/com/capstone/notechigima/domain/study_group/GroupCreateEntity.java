package com.capstone.notechigima.domain.study_group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GroupCreateEntity {
    private int ownerId;
    private int groupId;
    private String groupName;
}