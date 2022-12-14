package com.capstone.notechigima.domain.topic;


import com.capstone.notechigima.domain.BaseTimeEntity;
import com.capstone.notechigima.domain.VisibilityStatus;
import com.capstone.notechigima.domain.note.Note;
import com.capstone.notechigima.domain.subject.Subject;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Topic extends BaseTimeEntity {
    @Id
    @Column(name = "topic_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int topicId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VisibilityStatus status;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TopicAnalyzedType analyzed;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "SUBJECT_ID")
    private Subject subject;

    @OneToMany(mappedBy = "topic")
    private List<Note> notes = new ArrayList<>();

    @Builder
    public Topic(int topicId, VisibilityStatus status, String title, TopicAnalyzedType analyzed, Subject subject, List<Note> notes) {
        this.topicId = topicId;
        this.status = status;
        this.title = title;
        this.analyzed = analyzed;
        this.subject = subject;
        this.notes = notes;
    }

    public void updateAnalyzed(TopicAnalyzedType analyzed) {
        this.analyzed = analyzed;
    }
}
