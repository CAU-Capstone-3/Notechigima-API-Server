package com.capstone.notechigima.service;

import com.capstone.notechigima.config.BaseException;
import com.capstone.notechigima.model.dto.note.GetNoteResponseDTO;
import com.capstone.notechigima.model.dto.note.GetNoteSummarizedDTO;
import com.capstone.notechigima.model.dto.note.PostNoteRequestDTO;

import java.util.List;

public interface NoteService {

    List<GetNoteSummarizedDTO> getNoteList(int sectionId) throws BaseException;

    GetNoteResponseDTO getNote(int noteId) throws BaseException;

    int postNote(int sectionId, PostNoteRequestDTO body) throws BaseException;
}