package com.capstone.notechigima.service;

import com.capstone.notechigima.model.ModelMapper;
import com.capstone.notechigima.model.dao.advice.AdviceEntity;
import com.capstone.notechigima.model.dao.advice.AdviceType;
import com.capstone.notechigima.model.dao.sentence.SentenceEntity;
import com.capstone.notechigima.model.dao.sentence.SentenceWithWriterEntity;
import com.capstone.notechigima.model.dto.advice.AdviceInferenceRequestVO;
import com.capstone.notechigima.model.dto.topic.TopicResponseDTO;
import com.capstone.notechigima.repository.AdviceRepository;
import com.capstone.notechigima.repository.NoteRepository;
import com.capstone.notechigima.repository.TopicRepository;
import com.capstone.notechigima.repository.SentenceRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TopicServiceImpl implements TopicService {

    private static final String NLI_ULI = "http://18.189.150.89:5000/nli";

    private final AdviceRepository adviceRepository;
    private final SentenceRepository sentenceRepository;
    private final NoteRepository noteRepository;
    private final TopicRepository topicRepository;
    private final ModelMapper modelMapper;

    public TopicServiceImpl(AdviceRepository adviceRepository, SentenceRepository sentenceRepository, NoteRepository noteRepository, TopicRepository topicRepository, ModelMapper modelMapper) {
        this.adviceRepository = adviceRepository;
        this.sentenceRepository = sentenceRepository;
        this.noteRepository = noteRepository;
        this.topicRepository = topicRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TopicResponseDTO getTopic(int topicId) {
        return modelMapper.map(topicRepository.getTopic(topicId));
    }

    @Override
    public List<TopicResponseDTO> getTopicList(int subjectId) {
        return topicRepository.getTopicList(subjectId).stream()
                .map(entity -> modelMapper.map(entity)
                ).collect(Collectors.toList());
    }

    @Async("threadPoolTaskExecutor")
    @Override
    public void requestAnalysis(int topicId) {
        topicRepository.setTopicAnalyzed(topicId, 'R');

        ArrayList<AdviceEntity> advices = new ArrayList<>();
        List<SentenceWithWriterEntity> sentences = sentenceRepository.getSentenceListByTopicId(topicId);
        List<SentenceWithWriterEntity[]> sentComb = getComb(sentences);

        for (SentenceWithWriterEntity[] comb : sentComb) {
            if (isContradiction(comb[0].getContent(), comb[1].getContent())) {
                advices.add(new AdviceEntity(
                        0,
                        comb[0].getSentenceId(),
                        comb[1].getSentenceId(),
                        AdviceType.CONTRADICTION.getType()
                ));
            }
        }

        if (!advices.isEmpty())
            adviceRepository.insertAll(toMap(advices));
        topicRepository.setTopicAnalyzed(topicId, 'F');
    }

    private List<SentenceWithWriterEntity[]> getComb(List<SentenceWithWriterEntity> sentences) {
        ArrayList<SentenceWithWriterEntity[]> comb = new ArrayList<>();
        for (int i = 0; i < sentences.size(); i++) {
            for (int j = i + 1; j < sentences.size(); j++) {
                SentenceWithWriterEntity sent1 = sentences.get(i);
                SentenceWithWriterEntity sent2 = sentences.get(j);
                if (sent1.getWriterId() != sent2.getWriterId())
                    comb.add(new SentenceWithWriterEntity[] { sent1, sent2 });
            }
        }
        return comb;
    }

    private boolean isContradiction(String sent1, String sent2) {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = UriComponentsBuilder
                .fromUriString(NLI_ULI)
                .encode()
                .build()
                .toUri();
        AdviceInferenceRequestVO request = new AdviceInferenceRequestVO(
                sent1, sent2
        );
        String response = restTemplate.postForObject(uri, request, String.class);

        try {
            JSONObject jsonObject = new JSONObject(response);
            String result = jsonObject.getString("result");
            if (result.equals("contradiction"))
                return true;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static Map toMap(Object obj) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", obj);
        return map;
    }
}
