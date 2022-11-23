package com.capstone.notechigima.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class TopicEntity {
    private int topicId;
    private int topicName;
    private String title;
    private Date updatedAt;
    private char analyzed;
}
