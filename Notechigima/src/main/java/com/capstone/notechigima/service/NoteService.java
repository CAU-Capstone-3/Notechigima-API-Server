package com.capstone.notechigima.service;

import com.capstone.notechigima.config.BaseException;
import com.capstone.notechigima.dao.note.GetNoteResponseDAO;

public interface NoteService {

    public GetNoteResponseDAO getNote(int noteId) throws BaseException;
}
