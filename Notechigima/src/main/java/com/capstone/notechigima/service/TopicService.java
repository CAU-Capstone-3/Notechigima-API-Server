package com.capstone.notechigima.service;

import com.capstone.notechigima.dto.topic.TopicResponseDTO;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface TopicService {

    TopicResponseDTO getTopic(int topicId);

    List<TopicResponseDTO> getTopicList(int subjectId);

    @Async("threadPoolTaskExecutor")
    void requestAnalysis(int topicId);

}
