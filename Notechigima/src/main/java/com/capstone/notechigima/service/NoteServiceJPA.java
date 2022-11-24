package com.capstone.notechigima.service;

import com.capstone.notechigima.config.BaseException;
import com.capstone.notechigima.domain.note.NoteDetailEntity;
import com.capstone.notechigima.domain.note.NoteInsertEntity;
import com.capstone.notechigima.domain.sentence.SentenceEntity;
import com.capstone.notechigima.dto.ModelMapper;
import com.capstone.notechigima.dto.note.GetNoteResponseDTO;
import com.capstone.notechigima.dto.note.GetNoteSummarizedDTO;
import com.capstone.notechigima.dto.note.PostNoteRequestDTO;
import com.capstone.notechigima.dto.sentence.SentenceResponseDTO;
import com.capstone.notechigima.repository.NoteRepository;
import com.capstone.notechigima.repository.SentenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NoteServiceJPA {

    private final SentenceRepository sentenceRepository;
    private final NoteRepository noteRepository;
    private final ModelMapper modelMapper;

    public List<GetNoteSummarizedDTO> getNoteList(int topicId) throws BaseException {
        return noteRepository.getNoteList(topicId).stream()
                .map(entity -> modelMapper.map(entity)
                ).collect(Collectors.toList());
    }

    public GetNoteResponseDTO getNote(int noteId) throws BaseException {
        List<SentenceEntity> sentenceList = sentenceRepository.getSentenceListByNoteId(noteId);
        List<SentenceResponseDTO> sentenceResult =
                sentenceList.stream()
                        .map(entity -> modelMapper.map(entity))
                        .collect(Collectors.toList());

        NoteDetailEntity note = noteRepository.getNoteDetail(noteId);
        GetNoteResponseDTO result = modelMapper.map(note, sentenceResult);
        return result;
    }

    public int postNote(PostNoteRequestDTO body) throws BaseException {

        NoteInsertEntity entity = new NoteInsertEntity(0, body.getUserId(), body.getTopicId());
        int result = noteRepository.insertNote(entity);
        int noteId = entity.getId();

        List<String> sentences = Arrays.stream(body.getContent().split("\n")).toList();
        sentences.stream().forEach(str -> str = str.replaceAll("\n", ""));
        List<String> sentencesFiltered = sentences.stream().filter(str -> str != null && !str.isEmpty()).toList();

        ArrayList<SentenceEntity> sentenceEntities = new ArrayList<>();
        for (int i = 0; i < sentencesFiltered.size(); i++) {
            sentenceEntities.add(new SentenceEntity(body.getTopicId(), noteId, sentencesFiltered.get(i), 'N', i + 1));
        }

        Map<String, Object> sentenceMap = new HashMap<>();
        sentenceMap.put("list", sentenceEntities);
        sentenceRepository.insertAll(sentenceMap);

        return result;
    }
}
