package com.capstone.notechigima.service;

import com.capstone.notechigima.config.BaseException;
import com.capstone.notechigima.model.dto.note.GetNoteResponseDTO;
import com.capstone.notechigima.model.dto.note.PostNoteRequestDTO;

public interface NoteService {

    public GetNoteResponseDTO getNote(int noteId) throws BaseException;

    public int postNote(PostNoteRequestDTO body) throws BaseException;
}
