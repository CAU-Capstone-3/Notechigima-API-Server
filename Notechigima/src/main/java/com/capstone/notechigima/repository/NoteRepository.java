package com.capstone.notechigima.repository;

import com.capstone.notechigima.domain.note.Note;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {

    @EntityGraph(attributePaths = {"topic", "owner"})
    List<Note> findAllByTopic_TopicId(int topicId);
}
