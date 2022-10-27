package com.capstone.notechigima.model;

import com.capstone.notechigima.model.dao.note.NoteOwnerEntity;
import com.capstone.notechigima.model.dao.sentence.SentenceEntity;
import com.capstone.notechigima.model.dto.note.GetNoteSummarizedDTO;
import com.capstone.notechigima.model.dto.sentence.SentenceResponseDTO;

public class ModelMapper {

    public SentenceResponseDTO map(SentenceEntity entity) {
        return new SentenceResponseDTO(entity.getContent(), entity.getSequenceNum(), entity.getSentenceType());
    }

    public GetNoteSummarizedDTO map(NoteOwnerEntity entity) {
        return new GetNoteSummarizedDTO(entity.getNoteId(), entity.getOwnerId(), entity.getOwnerName(), entity.getUpdatedAt());
    }
}
