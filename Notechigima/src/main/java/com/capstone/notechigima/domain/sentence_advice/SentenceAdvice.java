package com.capstone.notechigima.domain.sentence_advice;

import com.capstone.notechigima.domain.BaseTimeEntity;
import com.capstone.notechigima.domain.sentence.Sentence;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "sentence_advice")
public class SentenceAdvice extends BaseTimeEntity {

    @Id
    @Column(name = "advice_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adviceId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AdviceType adviceType;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "SENTENCE1_ID", referencedColumnName = "SENTENCE_ID")
    private Sentence sentence1;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "SENTENCE2_ID", referencedColumnName = "SENTENCE_ID")
    private Sentence sentence2;

    @Builder
    public SentenceAdvice(int adviceId, AdviceType adviceType, Sentence sentence1, Sentence sentence2) {
        this.adviceId = adviceId;
        this.adviceType = adviceType;
        this.sentence1 = sentence1;
        this.sentence2 = sentence2;
    }
}
