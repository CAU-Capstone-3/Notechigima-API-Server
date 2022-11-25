package com.capstone.notechigima.repository;

import com.capstone.notechigima.domain.sentence.Sentence;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SentenceRepository extends JpaRepository<Sentence, Integer> {

    @EntityGraph(attributePaths = {"note"})
    List<Sentence> findAllByNote_NoteId(int nodeId);

    @EntityGraph(attributePaths = {"note"})
    List<Sentence> findAllByNote_Topic_TopicId(int topicId);

}
