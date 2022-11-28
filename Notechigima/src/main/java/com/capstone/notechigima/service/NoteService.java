package com.capstone.notechigima.service;

import com.capstone.notechigima.domain.VisibilityStatus;
import com.capstone.notechigima.domain.note.Note;
import com.capstone.notechigima.domain.sentence.Sentence;
import com.capstone.notechigima.domain.sentence.SentenceType;
import com.capstone.notechigima.domain.topic.Topic;
import com.capstone.notechigima.domain.users.User;
import com.capstone.notechigima.dto.note.NoteGetResponseDTO;
import com.capstone.notechigima.dto.note.NoteListGetResponseDTO;
import com.capstone.notechigima.dto.note.NotePostRequestDTO;
import com.capstone.notechigima.dto.sentence.SentenceListGetResponseDTO;
import com.capstone.notechigima.mapper.NoteMapper;
import com.capstone.notechigima.mapper.SentenceMapper;
import com.capstone.notechigima.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    public NoteGetResponseDTO getNote(int noteId) throws IllegalArgumentException, NoSuchElementException {
        List<Sentence> sentenceList = sentenceRepository.findAllByNote_NoteId(noteId);
        List<SentenceListGetResponseDTO> sentenceResult =
                sentenceList.stream()
                        .map(entity -> SentenceMapper.INSTANCE.toSentenceListGetResponseDTO(entity))
                        .collect(Collectors.toList());

        Note note = noteRepository.findById(noteId).orElseThrow();
        NoteGetResponseDTO result = NoteMapper.INSTANCE.toNoteGetResponseDTO(note, sentenceResult);
        return result;
    }

    public void postNote(NotePostRequestDTO body) throws IllegalArgumentException, NoSuchElementException {
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

        ArrayList<Sentence> sentenceEntities = new ArrayList<>();
        for (int i = 0; i < sentencesFiltered.size(); i++) {
            Sentence createSentence = Sentence.builder()
                    .note(entity)
                    .content(sentencesFiltered.get(i))
                    .sentenceType(SentenceType.PLAIN)
                    .sequenceNum(i + 1)
                    .build();
            sentenceEntities.add(createSentence);
        }

        sentenceRepository.saveAll(sentenceEntities);
    }
}
