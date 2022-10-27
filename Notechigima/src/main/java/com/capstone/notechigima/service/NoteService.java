package com.capstone.notechigima.service;

import com.capstone.notechigima.config.BaseException;
import com.capstone.notechigima.model.dto.note.GetNoteResponseDTO;

public interface NoteService {

    public GetNoteResponseDTO getNote(int noteId) throws BaseException;
}
