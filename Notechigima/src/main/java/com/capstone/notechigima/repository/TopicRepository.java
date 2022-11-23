package com.capstone.notechigima.repository;

import com.capstone.notechigima.domain.topic.Topic;
import com.capstone.notechigima.domain.topic.TopicEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Integer> {
}
