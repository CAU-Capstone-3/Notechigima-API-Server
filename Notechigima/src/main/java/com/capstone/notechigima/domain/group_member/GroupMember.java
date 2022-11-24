package com.capstone.notechigima.domain.group_member;

import com.capstone.notechigima.domain.BaseTimeEntity;
import com.capstone.notechigima.domain.study_group.StudyGroup;
import com.capstone.notechigima.domain.users.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@DynamicInsert
@IdClass(GroupMemberPK.class)
public class GroupMember extends BaseTimeEntity {

    @Enumerated(EnumType.STRING)
    @Column
    private GroupAccessType access;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "GROUP_ID")
    private StudyGroup studyGroup;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Builder
    public GroupMember(GroupAccessType access, StudyGroup studyGroup, User user) {
        this.access = access;
        this.studyGroup = studyGroup;
        this.user = user;
    }
}
