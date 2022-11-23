package com.capstone.notechigima.domain.invite;

import com.capstone.notechigima.domain.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class GroupInvite extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "groupInviteId")
    private int groupInviteId;

    @Column(nullable = false)
    private int userId;

    @Column(nullable = false)
    private int groupId;

    @Column(nullable = false)
    private char accepted;

}
