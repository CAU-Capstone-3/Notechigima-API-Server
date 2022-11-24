package com.capstone.notechigima.repository;

import com.capstone.notechigima.domain.sentence_advice.AdviceDetailEntity;
import com.capstone.notechigima.domain.sentence_advice.Advice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface AdviceRepository extends JpaRepository<Advice, Integer> {

    int insertAll(Map<String, Object> map);

    List<AdviceDetailEntity> getAdviceList(int topicId);

    @EntityGraph(attributePaths = {"sentence1"})
    List<Advice> findAllBySentence1_Note_Topic_TopicId(int topicId);
}
