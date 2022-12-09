package com.capstone.notechigima.service;

import com.capstone.notechigima.config.ExceptionCode;
import com.capstone.notechigima.config.RestApiException;
import com.capstone.notechigima.domain.VisibilityStatus;
import com.capstone.notechigima.domain.note.Note;
import com.capstone.notechigima.domain.topic.Topic;
import com.capstone.notechigima.domain.topic.TopicAnalyzedType;
import com.capstone.notechigima.domain.users.User;
import com.capstone.notechigima.dto.note.NoteGetResponseDTO;
import com.capstone.notechigima.dto.note.NoteListGetResponseDTO;
import com.capstone.notechigima.dto.note.NotePostRequestDTO;
import com.capstone.notechigima.mapper.NoteMapper;
import com.capstone.notechigima.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class NoteService {

    private final SentenceRepository sentenceRepository;
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;

    public List<NoteListGetResponseDTO> getNoteList(int topicId) {
        return noteRepository.findAllByTopic_TopicId(topicId).stream()
                .map(NoteMapper.INSTANCE::toNoteListGetResponseDTO
                ).toList();
    }

    public NoteGetResponseDTO getNote(int noteId) throws RestApiException {
        Note note = noteRepository.findById(noteId).orElseThrow(() -> {
            throw new RestApiException(ExceptionCode.ERROR_NOT_FOUND_RESOURCE);
        });

        return NoteMapper.INSTANCE.toNoteGetResponseDTO(note);
    }

    public void postNote(NotePostRequestDTO body) throws RestApiException {
        User owner = userRepository.findById(body.getUserId()).orElseThrow(() -> {
            throw new RestApiException(ExceptionCode.ERROR_NOT_FOUND_USER);
        });
        Topic topic = topicRepository.findById(body.getTopicId()).orElseThrow(() -> {
            throw new RestApiException(ExceptionCode.ERROR_NOT_FOUND_RESOURCE);
        });

        validationDuplicate(body.getUserId(), body.getTopicId());

        Note entity = Note.builder()
                .owner(owner)
                .topic(topic)
                .status(VisibilityStatus.VISIBLE)
                .content(body.getContent())
                .build();

        noteRepository.save(entity);

        int groupMemberSize = topic.getSubject().getStudyGroup().getMembers().size();
        if (topic.getNotes().size() == groupMemberSize) {
            topic.updateAnalyzed(TopicAnalyzedType.READY);
            topicRepository.save(topic);
        }
    }

    private void validationDuplicate(int userId, int topicId) throws RestApiException {
        List<Note> shouldEmpty = noteRepository.findByOwner_UserIdAndTopic_TopicId(userId, topicId);
        if (!shouldEmpty.isEmpty())
            throw new RestApiException(ExceptionCode.ERROR_DUPLICATED_NOTE);
    }
}
