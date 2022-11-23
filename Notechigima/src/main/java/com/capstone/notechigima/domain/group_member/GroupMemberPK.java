package com.capstone.notechigima.domain.group_member;

import com.capstone.notechigima.domain.study_group.StudyGroup;
import com.capstone.notechigima.domain.users.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class GroupMemberPK implements Serializable {
    private StudyGroup studyGroup;
    private User user;
}
