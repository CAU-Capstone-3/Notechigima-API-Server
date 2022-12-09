package com.capstone.notechigima.repository;

import com.capstone.notechigima.domain.advice_sentence.AdviceSentence;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdviceSentenceRepository extends JpaRepository<AdviceSentence, Integer> {

    @EntityGraph(attributePaths = {"advice"})
    List<AdviceSentence> findAllByAdvice_Topic_TopicId(int topicId);
}
