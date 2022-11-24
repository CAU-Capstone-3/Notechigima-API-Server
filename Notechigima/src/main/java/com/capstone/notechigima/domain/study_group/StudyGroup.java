package com.capstone.notechigima.domain.study_group;

import com.capstone.notechigima.domain.ActiveStatus;
import com.capstone.notechigima.domain.BaseTimeEntity;
import com.capstone.notechigima.domain.group_member.GroupMember;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@DynamicInsert
@Entity
public class StudyGroup extends BaseTimeEntity {

    @Id
    @Column(name = "GROUP_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupId;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @ColumnDefault("ActiveStatus.ACTIVE")
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
