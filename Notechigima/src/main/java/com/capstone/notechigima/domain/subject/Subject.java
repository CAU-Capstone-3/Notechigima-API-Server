package com.capstone.notechigima.domain.subject;

import com.capstone.notechigima.domain.BaseTimeEntity;
import com.capstone.notechigima.domain.VisibilityStatus;
import com.capstone.notechigima.domain.study_group.StudyGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Subject extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subjectId;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VisibilityStatus status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "GROUP_ID")
    private StudyGroup studyGroup;

    @Builder
    public Subject(int subjectId, String name, VisibilityStatus status, StudyGroup studyGroup) {
        this.subjectId = subjectId;
        this.name = name;
        this.status = status;
        this.studyGroup = studyGroup;
    }
}
