package com.capstone.notechigima.domain.topic;


import com.capstone.notechigima.domain.BaseTimeEntity;
import com.capstone.notechigima.domain.VisibilityStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Topic extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int topicId;

    @Column(nullable = false)
    private int subjectId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VisibilityStatus status;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TopicAnalyzedType analyzed;

    @Builder
    public Topic(int topicId, int subjectId, VisibilityStatus status, String title, TopicAnalyzedType analyzed) {
        this.topicId = topicId;
        this.subjectId = subjectId;
        this.status = status;
        this.title = title;
        this.analyzed = analyzed;
    }

    public void updateAnalyzed(TopicAnalyzedType analyzed) {
        this.analyzed = analyzed;
    }
}
