package com.capstone.notechigima.domain.invite;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
public class GroupInvite {

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

    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = false)
    private Date updatedAt;

}
