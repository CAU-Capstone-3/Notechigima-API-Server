package com.capstone.notechigima.repository;

import com.capstone.notechigima.domain.NoteDetailEntity;
import com.capstone.notechigima.domain.NoteInsertEntity;
import com.capstone.notechigima.domain.NoteOwnerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NoteRepository {

    List<NoteOwnerEntity> getNoteList(int sectionId);

    NoteDetailEntity getNoteDetail(int noteId);

    int insertNote(NoteInsertEntity body);
}
