package com.capstone.notechigima.service;

import com.capstone.notechigima.model.ModelMapper;
import com.capstone.notechigima.model.dao.advice.AdviceEntity;
import com.capstone.notechigima.model.dao.note.NoteOwnerEntity;
import com.capstone.notechigima.model.dao.sentence.SentenceEntity;
import com.capstone.notechigima.model.dto.advice.AdviceInferenceRequestDTO;
import com.capstone.notechigima.model.dto.topic.TopicResponseDTO;
import com.capstone.notechigima.repository.AdviceRepository;
import com.capstone.notechigima.repository.NoteRepository;
import com.capstone.notechigima.repository.TopicRepository;
import com.capstone.notechigima.repository.SentenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TopicServiceImpl implements TopicService {

    private static final String NLI_ULI = "http://9fee-34-126-182-175.ngrok.io/";

    @Autowired
    private final AdviceRepository adviceRepository;

    @Autowired
    private final SentenceRepository sentenceRepository;

    @Autowired
    private final NoteRepository noteRepository;

    @Autowired
    private final TopicRepository topicRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public TopicServiceImpl(AdviceRepository adviceRepository, SentenceRepository sentenceRepository, NoteRepository noteRepository, TopicRepository topicRepository, ModelMapper modelMapper) {
        this.adviceRepository = adviceRepository;
        this.sentenceRepository = sentenceRepository;
        this.noteRepository = noteRepository;
        this.topicRepository = topicRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TopicResponseDTO> getSectionList(int subjectId) {
        return topicRepository.getTopicList(subjectId).stream()
                .map(entity -> modelMapper.map(entity)
                ).collect(Collectors.toList());
    }

    @Override
    public int requestAnalysis(int topicId) {

        ArrayList<AdviceEntity> advices = new ArrayList<>();

        List<NoteOwnerEntity> noteList = noteRepository.getNoteList(topicId);
        Map<Integer, List<SentenceEntity>> noteSentList = new HashMap<>();

        RestTemplate restTemplate = new RestTemplate();
        URI uri = UriComponentsBuilder
                .fromUriString(NLI_ULI)
                .path("/nli")
                .encode()
                .build()
                .toUri();

        for (NoteOwnerEntity noteEntity : noteList) {
            List<SentenceEntity> sentenceList = sentenceRepository.getSentenceList(noteEntity.getNoteId());
            noteSentList.put(noteEntity.getNoteId(), sentenceList);
        }

        for (Map.Entry<Integer, List<SentenceEntity>> elem : noteSentList.entrySet()) {
            ArrayList<String> doc = new ArrayList<>();
            for (Map.Entry<Integer, List<SentenceEntity>> docElem : noteSentList.entrySet())
                if (docElem.getKey() != elem.getKey()) {
                    List<String> contentList = docElem.getValue().stream().map(entity -> entity.getContent()).collect(Collectors.toList());
                    doc.addAll(contentList);
                }

            for (SentenceEntity sentEntity : elem.getValue()) {
                AdviceInferenceRequestDTO request = new AdviceInferenceRequestDTO(
                        doc, sentEntity.getContent()
                );
                String response = restTemplate.postForObject(uri, request, String.class);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    double result = Double.parseDouble(jsonObject.getString("result"));

                    if (result < 1)
                        advices.add(new AdviceEntity(0, sentEntity.getSentenceId(), 'D'));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        Map<String, Object> adviceMap = new HashMap<>();
        adviceMap.put("list", advices);
        return adviceRepository.insertAll(adviceMap);
    }
}
