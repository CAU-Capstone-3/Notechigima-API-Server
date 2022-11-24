package com.capstone.notechigima.mapper;

import com.capstone.notechigima.domain.note.Note;
import com.capstone.notechigima.dto.note.NoteGetResponseDTO;
import com.capstone.notechigima.dto.note.NoteListGetResponseDTO;
import com.capstone.notechigima.dto.sentence.SentenceResponseDTO;
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

    // TODO : subject 완료 후 구현 예정
    default NoteGetResponseDTO toNoteGetResponseDTO(Note note, List<SentenceResponseDTO> sentenceList) {
        return NoteGetResponseDTO.builder()
                .sentenceList(sentenceList)
                .build();
    }
}
