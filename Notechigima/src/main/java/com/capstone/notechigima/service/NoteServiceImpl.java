package com.capstone.notechigima.service;

import com.capstone.notechigima.config.BaseException;
import com.capstone.notechigima.model.note.GetNoteResponseModel;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImpl implements NoteService {

    @Override
    public GetNoteResponseModel getNote(int noteId) throws BaseException {
        return null;
    }
}
