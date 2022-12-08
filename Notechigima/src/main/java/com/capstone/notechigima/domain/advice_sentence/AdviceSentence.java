package com.capstone.notechigima.domain.advice_sentence;

import com.capstone.notechigima.domain.BaseTimeEntity;
import com.capstone.notechigima.domain.advice.Advice;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "advice_sentence")
public class AdviceSentence extends BaseTimeEntity {

    @Id
    @Column(name = "advice_sentence_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adviceSentenceId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "advice_id", referencedColumnName = "advice_id")
    private Advice advice;

    @Column
    private String content;

    @Column
    private boolean represent;

    @Builder
    public AdviceSentence(int adviceSentenceId, Advice advice, String content, boolean represent) {
        this.adviceSentenceId = adviceSentenceId;
        this.advice = advice;
        this.content = content;
        this.represent = represent;
    }
}
