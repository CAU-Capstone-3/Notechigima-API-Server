package com.capstone.notechigima.service;

import com.capstone.notechigima.config.BaseException;
import com.capstone.notechigima.dto.note.NoteGetResopnseDTO;
import com.capstone.notechigima.dto.note.NoteListGetResponseDTO;
import com.capstone.notechigima.dto.note.PostNoteRequestDTO;

import java.util.List;

public interface NoteService {

    List<NoteListGetResponseDTO> getNoteList(int topicId) throws BaseException;

    NoteGetResopnseDTO getNote(int noteId) throws BaseException;

    int postNote(PostNoteRequestDTO body) throws BaseException;
}
