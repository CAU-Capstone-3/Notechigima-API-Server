package com.capstone.notechigima.repository;

import com.capstone.notechigima.domain.topic.TopicEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TopicRepository {

    TopicEntity getTopic(int topicId);

    List<TopicEntity> getTopicList(int subjectId);

    int setTopicAnalyzed(int topicId, char analyzed);

}
