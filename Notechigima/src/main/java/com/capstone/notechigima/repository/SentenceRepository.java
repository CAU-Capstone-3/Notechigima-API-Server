package com.capstone.notechigima.repository;

import com.capstone.notechigima.domain.sentence.Sentence;
import com.capstone.notechigima.domain.sentence.SentenceEntity;
import com.capstone.notechigima.domain.sentence.SentenceWithWriterEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

public interface SentenceRepository extends JpaRepository<Sentence, Integer> {

    @EntityGraph(attributePaths = {"note"})
    List<Sentence> findAllByNote_NoteId(int nodeId);

    @EntityGraph(attributePaths = {"note"})
    List<Sentence> findAllByNote_Topic_TopicId(int topicId);

}
