package com.capstone.notechigima.service;

import com.capstone.notechigima.config.BaseException;
import com.capstone.notechigima.dto.note.NoteGetResponseDTO;
import com.capstone.notechigima.dto.note.NoteListGetResponseDTO;
import com.capstone.notechigima.dto.note.NotePostRequestDTO;

import java.util.List;

public interface NoteService {

    List<NoteListGetResponseDTO> getNoteList(int topicId) throws BaseException;

    NoteGetResponseDTO getNote(int noteId) throws BaseException;

    int postNote(NotePostRequestDTO body) throws BaseException;
}
