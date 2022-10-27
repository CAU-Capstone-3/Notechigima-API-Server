package com.capstone.notechigima.service;

import com.capstone.notechigima.config.BaseException;
import com.capstone.notechigima.dao.note.GetNoteResponseDAO;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImpl implements NoteService {

    @Override
    public GetNoteResponseDAO getNote(int noteId) throws BaseException {
        return null;
    }
}
