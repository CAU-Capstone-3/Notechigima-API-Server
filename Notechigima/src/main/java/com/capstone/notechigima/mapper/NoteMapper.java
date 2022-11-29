package com.capstone.notechigima.mapper;

import com.capstone.notechigima.domain.note.Note;
import com.capstone.notechigima.dto.note.NoteGetResponseDTO;
import com.capstone.notechigima.dto.note.NoteListGetResponseDTO;
import com.capstone.notechigima.dto.sentence.SentenceListGetResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface NoteMapper {
    NoteMapper INSTANCE = Mappers.getMapper(NoteMapper.class);

    default NoteListGetResponseDTO toNoteListGetResponseDTO(Note note) {
        return NoteListGetResponseDTO.builder()
                .ownerId(note.getOwner().getUserId())
                .ownerName(note.getOwner().getNickname())
                .noteId(note.getNoteId())
                .updatedAt(note.getUpdatedAt())
                .build();
    }

    default NoteGetResponseDTO toNoteGetResponseDTO(Note note, List<SentenceListGetResponseDTO> sentenceList) {
        return NoteGetResponseDTO.builder()
                .subjectId(note.getTopic().getSubject().getSubjectId())
                .subjectName(note.getTopic().getSubject().getName())
                .topicId(note.getTopic().getTopicId())
                .topicName(note.getTopic().getTitle())
                .noteId(note.getNoteId())
                .lastUpdate(note.getUpdatedAt())
                .writerId(note.getOwner().getUserId())
                .writerName(note.getOwner().getNickname())
                .sentences(sentenceList)
                .build();
    }
}
