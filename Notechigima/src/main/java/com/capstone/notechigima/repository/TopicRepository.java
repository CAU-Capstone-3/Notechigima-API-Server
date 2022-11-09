package com.capstone.notechigima.repository;

import com.capstone.notechigima.model.dao.topic.TopicEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TopicRepository {

    List<TopicEntity> getTopicList(int subjectId);

}