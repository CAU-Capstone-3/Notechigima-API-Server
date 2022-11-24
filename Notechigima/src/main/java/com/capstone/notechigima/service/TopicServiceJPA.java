package com.capstone.notechigima.service;

import com.capstone.notechigima.domain.sentence_advice.AdviceEntity;
import com.capstone.notechigima.domain.sentence_advice.AdviceType;
import com.capstone.notechigima.domain.sentence.SentenceWithWriterEntity;
import com.capstone.notechigima.domain.topic.Topic;
import com.capstone.notechigima.domain.topic.TopicAnalyzedType;
import com.capstone.notechigima.dto.ModelMapper;
import com.capstone.notechigima.dto.advice.AdviceInferenceRequestVO;
import com.capstone.notechigima.dto.topic.TopicResponseDTO;
import com.capstone.notechigima.repository.AdviceRepository;
import com.capstone.notechigima.repository.SentenceRepository;
import com.capstone.notechigima.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TopicServiceJPA {
    private static final String NLI_ULI = "http://18.189.150.89:5000/nli";

    private final TopicRepository topicRepository;
    private final AdviceRepository adviceRepository;
    private final SentenceRepository sentenceRepository;
    private final ModelMapper modelMapper;

    public TopicResponseDTO getTopic(int topicId) throws NoSuchElementException {
        return modelMapper.map(topicRepository.findById(topicId).orElseThrow());
    }

    public List<TopicResponseDTO> getTopicList(int subjectId) {
        return topicRepository.findAllBySubjectId(subjectId).stream()
                .map(entity -> modelMapper.map(entity)
                ).collect(Collectors.toList());
    }

    @Transactional
    @Async("threadPoolTaskExecutor")
    public void requestAnalysis(int topicId) {
        Topic topicToUpdate = topicRepository.findById(topicId).orElseThrow();
        topicToUpdate.updateAnalyzed(TopicAnalyzedType.RUNNING);
        topicRepository.save(topicToUpdate);

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

        topicToUpdate.updateAnalyzed(TopicAnalyzedType.FINISH);
        topicRepository.save(topicToUpdate);
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
