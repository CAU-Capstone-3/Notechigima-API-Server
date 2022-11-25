package com.capstone.notechigima.repository;

import com.capstone.notechigima.domain.topic.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TopicRepository extends JpaRepository<Topic, Integer> {

    List<Topic> findAllBySubjectId(int subjectId);
}
