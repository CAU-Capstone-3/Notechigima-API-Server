package com.capstone.notechigima.domain.group_invite;

import com.capstone.notechigima.domain.BaseTimeEntity;
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

    @Column(nullable = false)
    private int userId;

    @Column(nullable = false)
    private int groupId;

    @Column(nullable = false)
    private char accepted;

    @Builder
    public GroupInvite(int groupInviteId, int userId, int groupId, char accepted) {
        this.groupInviteId = groupInviteId;
        this.userId = userId;
        this.groupId = groupId;
        this.accepted = accepted;
    }
}
