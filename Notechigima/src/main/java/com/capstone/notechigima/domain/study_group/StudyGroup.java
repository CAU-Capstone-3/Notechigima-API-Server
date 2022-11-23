package com.capstone.notechigima.domain.study_group;

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
public class StudyGroup extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupId;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ActiveStatus status;

    @OneToMany(mappedBy = "studyGroup")
    private List<GroupMember> members = new ArrayList<>();

    @Builder
    public StudyGroup(int groupId, String name, ActiveStatus status) {
        this.groupId = groupId;
        this.name = name;
        this.status = status;
    }
}
