package com.capstone.notechigima.domain.group_member;

import com.capstone.notechigima.domain.BaseTimeEntity;
import com.capstone.notechigima.domain.study_group.StudyGroup;
import com.capstone.notechigima.domain.users.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@IdClass(GroupMemberPK.class)
public class GroupMember extends BaseTimeEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GroupAccessType access;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", foreignKey = @ForeignKey(name = "FK_GROUP_MEMBER_GROUP_ID"))
    private StudyGroup studyGroup;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_GROUP_MEMBER_USER_ID"))
    private User user;

    @Builder
    public GroupMember(GroupAccessType access) {
        this.access = access;
    }
}
