package com.capstone.notechigima.repository;

import com.capstone.notechigima.domain.note.Note;
import com.capstone.notechigima.domain.note.NoteDetailEntity;
import com.capstone.notechigima.domain.note.NoteInsertEntity;
import com.capstone.notechigima.domain.note.NoteOwnerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {

    @EntityGraph(attributePaths = {"topic", "owner"})
    List<Note> findAllByTopic_TopicId(int topicId);
}
