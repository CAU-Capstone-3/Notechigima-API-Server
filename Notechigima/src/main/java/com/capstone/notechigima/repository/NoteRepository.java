package com.capstone.notechigima.repository;

import com.capstone.notechigima.domain.note.NoteDetailEntity;
import com.capstone.notechigima.domain.note.NoteInsertEntity;
import com.capstone.notechigima.domain.note.NoteOwnerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NoteRepository {

    List<NoteOwnerEntity> getNoteList(int sectionId);

    NoteDetailEntity getNoteDetail(int noteId);

    int insertNote(NoteInsertEntity body);
}
