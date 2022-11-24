package com.capstone.notechigima.domain.users;

import com.capstone.notechigima.domain.ActiveStatus;
import com.capstone.notechigima.domain.BaseTimeEntity;
import com.capstone.notechigima.domain.group_member.GroupMember;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ActiveStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<GroupMember> memberGroups = new ArrayList<>();

    @Builder
    public User(int userId, String email, String nickname, ActiveStatus status, UserRole role) {
        this.userId = userId;
        this.email = email;
        this.nickname = nickname;
        this.status = status;
        this.role = role;
    }
}
