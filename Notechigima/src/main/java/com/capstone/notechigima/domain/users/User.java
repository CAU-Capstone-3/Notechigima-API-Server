package com.capstone.notechigima.domain.users;

import com.capstone.notechigima.domain.ActiveStatus;
import com.capstone.notechigima.domain.BaseTimeEntity;
import com.capstone.notechigima.domain.group_member.GroupMember;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false)
    @Size(min = 6, max = 20)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @ColumnDefault("ActiveStatus.ACTIVE")
    private ActiveStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @ColumnDefault("UserRole.USER")
    private UserRole role;

    @OneToMany(mappedBy = "user")
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
