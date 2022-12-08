package com.capstone.notechigima.domain.advice;

import com.capstone.notechigima.domain.BaseTimeEntity;
import com.capstone.notechigima.domain.sentence.Sentence;
import com.capstone.notechigima.domain.topic.Topic;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Builder
    public Advice(int adviceId, Topic topic, String content) {
        this.adviceId = adviceId;
        this.topic = topic;
        this.content = content;
    }
}
