package com.capstone.notechigima.model.dto.topic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class TopicResponseDTO {
    private int topicId;
    private String title;
    private Date updatedAt;
    private boolean analyzed;
}
