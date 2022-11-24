package com.capstone.notechigima.service;

import com.capstone.notechigima.config.BaseException;
import com.capstone.notechigima.domain.VisibilityStatus;
import com.capstone.notechigima.domain.note.Note;
import com.capstone.notechigima.domain.sentence.SentenceEntity;
import com.capstone.notechigima.domain.topic.Topic;
import com.capstone.notechigima.domain.users.User;
import com.capstone.notechigima.dto.ModelMapper;
import com.capstone.notechigima.dto.note.NoteGetResponseDTO;
import com.capstone.notechigima.dto.note.NoteListGetResponseDTO;
import com.capstone.notechigima.dto.note.PostNoteRequestDTO;
import com.capstone.notechigima.dto.sentence.SentenceResponseDTO;
import com.capstone.notechigima.mapper.NoteMapper;
import com.capstone.notechigima.repository.NoteRepository;
import com.capstone.notechigima.repository.SentenceRepository;
import com.capstone.notechigima.repository.TopicRepository;
import com.capstone.notechigima.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NoteServiceJPA {

    private final SentenceRepository sentenceRepository;
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;
    private final ModelMapper modelMapper;

    public List<NoteListGetResponseDTO> getNoteList(int topicId) throws BaseException {
        return noteRepository.findAllByTopic_TopicId(topicId).stream()
                .map(entity -> NoteMapper.INSTANCE.toNoteListGetResponseDTO(entity)
                ).collect(Collectors.toList());
    }

    public NoteGetResponseDTO getNote(int noteId) throws BaseException {
        List<SentenceEntity> sentenceList = sentenceRepository.getSentenceListByNoteId(noteId);
        List<SentenceResponseDTO> sentenceResult =
                sentenceList.stream()
                        .map(entity -> modelMapper.map(entity))
                        .collect(Collectors.toList());

        Note note = noteRepository.findById(noteId).orElseThrow();
        NoteGetResponseDTO result = NoteMapper.INSTANCE.toNoteGetResponseDTO(note, sentenceResult);
        return result;
    }

    public void postNote(PostNoteRequestDTO body) throws BaseException {
        User owner = userRepository.findById(body.getUserId()).orElseThrow();
        Topic topic = topicRepository.findById(body.getTopicId()).orElseThrow();

        Note entity = Note.builder()
                .owner(owner)
                .topic(topic)
                .status(VisibilityStatus.VISIBLE)
                .build();

        noteRepository.save(entity);

        List<String> sentences = Arrays.stream(body.getContent().split("\n")).toList();
        sentences.stream().forEach(str -> str = str.replaceAll("\n", ""));
        List<String> sentencesFiltered = sentences.stream().filter(str -> str != null && !str.isEmpty()).toList();

        ArrayList<SentenceEntity> sentenceEntities = new ArrayList<>();
        for (int i = 0; i < sentencesFiltered.size(); i++) {
            sentenceEntities.add(new SentenceEntity(body.getTopicId(), entity.getNoteId(), sentencesFiltered.get(i), 'N', i + 1));
        }

        Map<String, Object> sentenceMap = new HashMap<>();
        sentenceMap.put("list", sentenceEntities);
        sentenceRepository.insertAll(sentenceMap);

    }
}
