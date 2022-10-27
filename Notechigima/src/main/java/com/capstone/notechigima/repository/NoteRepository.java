package com.capstone.notechigima.repository;

import com.capstone.notechigima.model.dao.NoteEntity;
import com.capstone.notechigima.model.dto.note.PostNoteRequestDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface NoteRepository {

    NoteEntity getNoteDetail(int noteId);

    int insertNote(PostNoteRequestDTO body);
}
