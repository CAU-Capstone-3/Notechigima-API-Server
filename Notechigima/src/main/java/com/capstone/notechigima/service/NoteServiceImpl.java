package com.capstone.notechigima.service;

import com.capstone.notechigima.config.BaseException;
import com.capstone.notechigima.model.ModelMapper;
import com.capstone.notechigima.model.dao.NoteEntity;
import com.capstone.notechigima.model.dao.SentenceEntity;
import com.capstone.notechigima.model.dto.advice.AdviceResponseDTO;
import com.capstone.notechigima.model.dto.note.GetNoteResponseDTO;
import com.capstone.notechigima.model.dto.sentence.SentenceResponseDTO;
import com.capstone.notechigima.repository.NoteRepository;
import com.capstone.notechigima.repository.SentenceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {

    private final SentenceRepository sentenceRepository;

    private final NoteRepository noteRepository;

    private final ModelMapper modelMapper;

    public NoteServiceImpl(SentenceRepository sentenceRepository, NoteRepository noteRepository, ModelMapper modelMapper) {
        this.sentenceRepository = sentenceRepository;
        this.noteRepository = noteRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public GetNoteResponseDTO getNote(int noteId) throws BaseException {
        List<SentenceEntity> sentenceList = sentenceRepository.getSentenceList(noteId);
        List<SentenceResponseDTO> sentenceResult =
                sentenceList.stream()
                    .map(entity -> modelMapper.map(entity))
                    .collect(Collectors.toList());

        NoteEntity note = noteRepository.getNoteDetail(noteId);

        GetNoteResponseDTO result = new GetNoteResponseDTO(
                note.getSubjectId(),
                note.getSubjectName(),
                note.getSectionId(),
                note.getSectionName(),
                note.getNoteId(),
                note.getOwnerId(),
                note.getOwnerName(),
                sentenceResult,
                new ArrayList< AdviceResponseDTO >(),
                note.getUpdatedAt()
        );
        return result;
    }

}
