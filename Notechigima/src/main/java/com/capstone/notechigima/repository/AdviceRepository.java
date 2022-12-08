package com.capstone.notechigima.repository;

import com.capstone.notechigima.domain.advice.Advice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdviceRepository extends JpaRepository<Advice, Integer> {

    @EntityGraph(attributePaths = {"topic"})
    List<Advice> findAllByTopic_TopicId(int topicId);
}
