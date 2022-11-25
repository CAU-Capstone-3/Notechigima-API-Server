package com.capstone.notechigima.domain.sentence;

import com.capstone.notechigima.domain.BaseTimeEntity;
import com.capstone.notechigima.domain.note.Note;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "sentence_unit")
public class Sentence extends BaseTimeEntity {

    @Id
    @Column(name = "SENTENCE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sentenceId;

    @Column
    private int sequenceNum;

    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SentenceType sentenceType;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "NOTE_ID")
    private Note note;

    @Builder
    public Sentence(int sentenceId, int sequenceNum, String content, SentenceType sentenceType, Note note) {
        this.sentenceId = sentenceId;
        this.sequenceNum = sequenceNum;
        this.content = content;
        this.sentenceType = sentenceType;
        this.note = note;
    }
}
