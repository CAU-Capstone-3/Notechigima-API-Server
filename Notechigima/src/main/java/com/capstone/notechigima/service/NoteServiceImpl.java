package com.capstone.notechigima.service;

import com.capstone.notechigima.config.BaseException;
import com.capstone.notechigima.model.ModelMapper;
import com.capstone.notechigima.model.dao.sentence.SentenceEntity;
import com.capstone.notechigima.model.dto.sentence.SentenceResponseDTO;
import com.capstone.notechigima.repository.SentenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private SentenceRepository sentenceRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SentenceResponseDTO> getNote(int noteId) throws BaseException {
        List<SentenceEntity> list = sentenceRepository.getSentenceList(noteId);
        List<SentenceResponseDTO> resultList = list.stream().map(entity -> modelMapper.map(entity)).collect(Collectors.toList());

        return resultList;
    }

}
