package com.capstone.notechigima.domain.advice;

import com.capstone.notechigima.domain.BaseTimeEntity;
import com.capstone.notechigima.domain.advice_sentence.AdviceSentence;
import com.capstone.notechigima.domain.sentence.Sentence;
import com.capstone.notechigima.domain.topic.Topic;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "advice")
public class Advice extends BaseTimeEntity {

    @Id
    @Column(name = "advice_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adviceId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "topic_id", referencedColumnName = "topic_id")
    private Topic topic;

    @Column
    private String content;

    @Column(name = "advice_type")
    @Enumerated(EnumType.STRING)
    private AdviceType adviceType;

    @OneToMany(mappedBy = "advice")
    private List<AdviceSentence> adviceSentence = new ArrayList<>();

    @Builder
    public Advice(int adviceId, Topic topic, String content, AdviceType adviceType, List<AdviceSentence> adviceSentence) {
        this.adviceId = adviceId;
        this.topic = topic;
        this.content = content;
        this.adviceType = adviceType;
        this.adviceSentence = adviceSentence;
    }
}
