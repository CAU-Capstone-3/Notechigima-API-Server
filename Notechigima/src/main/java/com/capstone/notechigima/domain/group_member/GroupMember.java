package com.capstone.notechigima.domain.group_member;

import com.capstone.notechigima.domain.BaseTimeEntity;
import com.capstone.notechigima.domain.study_group.StudyGroup;
import com.capstone.notechigima.domain.users.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
@IdClass(GroupMemberPK.class)
public class GroupMember extends BaseTimeEntity implements Serializable {

    @Column(nullable = false)
    private GroupAccessType access;

    @Id
    @ManyToOne
    @JoinColumn(name = "group_id")
    private StudyGroup studyGroup;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @Builder
    public GroupMember(GroupAccessType access) {
        this.access = access;
    }
}
