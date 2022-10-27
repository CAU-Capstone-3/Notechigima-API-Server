package com.capstone.notechigima.service;

import com.capstone.notechigima.config.BaseException;
import com.capstone.notechigima.model.dto.sentence.SentenceResponseDTO;

import java.util.List;

public interface NoteService {

    public List<SentenceResponseDTO> getNote(int noteId) throws BaseException;
}
