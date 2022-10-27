package com.capstone.notechigima.service;

import com.capstone.notechigima.config.BaseException;
import com.capstone.notechigima.model.dto.note.GetNoteResponseDTO;
import com.capstone.notechigima.model.dto.note.GetNoteSummarizedDTO;
import com.capstone.notechigima.model.dto.note.PostNoteRequestDTO;

import java.util.List;

public interface NoteService {

    public List<GetNoteSummarizedDTO> getNoteList(int sectionId) throws BaseException;

    public GetNoteResponseDTO getNote(int noteId) throws BaseException;

    public int postNote(PostNoteRequestDTO body) throws BaseException;
}
