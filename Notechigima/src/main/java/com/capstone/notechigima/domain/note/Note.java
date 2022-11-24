package com.capstone.notechigima.domain.note;

import com.capstone.notechigima.domain.BaseTimeEntity;
import com.capstone.notechigima.domain.VisibilityStatus;
import com.capstone.notechigima.domain.topic.Topic;
import com.capstone.notechigima.domain.users.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Note extends BaseTimeEntity {

    @Id
    @Column(name = "NOTE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int noteId;

    @Enumerated(EnumType.STRING)
    @Column
    private VisibilityStatus status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "OWNER_ID")
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "TOPIC_ID")
    private Topic topic;

    @Builder
    public Note(int noteId, VisibilityStatus status, User owner, Topic topic) {
        this.noteId = noteId;
        this.status = status;
        this.owner = owner;
        this.topic = topic;
    }
}
