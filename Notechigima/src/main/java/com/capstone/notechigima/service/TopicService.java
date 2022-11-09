package com.capstone.notechigima.service;

import com.capstone.notechigima.model.dto.topic.TopicResponseDTO;

import java.util.List;

public interface TopicService {

    List<TopicResponseDTO> getSectionList(int subjectId);

    int requestAnalysis(int topicId);

}
