package com.capstone.notechigima.domain.group_invite;

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
public class GroupInvite extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupInviteId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AcceptType accepted;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "GROUP_ID")
    private StudyGroup studyGroup;

    @Builder
    public GroupInvite(int groupInviteId, AcceptType accepted, User user, StudyGroup studyGroup) {
        this.groupInviteId = groupInviteId;
        this.accepted = accepted;
        this.user = user;
        this.studyGroup = studyGroup;
    }
}
