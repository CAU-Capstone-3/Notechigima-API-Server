package com.capstone.notechigima.service;

import com.capstone.notechigima.config.BaseException;
import com.capstone.notechigima.model.note.GetNoteResponseModel;

public interface NoteService {

    public GetNoteResponseModel getNote(int noteId) throws BaseException;
}
