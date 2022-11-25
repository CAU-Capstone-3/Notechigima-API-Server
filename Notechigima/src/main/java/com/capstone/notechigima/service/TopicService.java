package com.capstone.notechigima.service;

import com.capstone.notechigima.domain.sentence.Sentence;
import com.capstone.notechigima.domain.sentence_advice.Advice;
import com.capstone.notechigima.domain.sentence_advice.AdviceType;
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
public class TopicService {
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

        ArrayList<Advice> advices = new ArrayList<>();
        List<Sentence> sentences = sentenceRepository.findAllByNote_Topic_TopicId(topicId);
        List<Sentence[]> sentComb = getComb(sentences);

        for (Sentence[] comb : sentComb) {
            if (isContradiction(comb[0].getContent(), comb[1].getContent())) {
                advices.add(
                        Advice.builder()
                                .sentence1(comb[0])
                                .sentence2(comb[1])
                                .adviceType(AdviceType.CONTRADICTION)
                                .build());
            }
        }

        if (!advices.isEmpty())
            adviceRepository.saveAll(advices);

        topicToUpdate.updateAnalyzed(TopicAnalyzedType.FINISH);
        topicRepository.save(topicToUpdate);
    }

    private List<Sentence[]> getComb(List<Sentence> sentences) {
        ArrayList<Sentence[]> comb = new ArrayList<>();
        for (int i = 0; i < sentences.size(); i++) {
            for (int j = i + 1; j < sentences.size(); j++) {
                Sentence sent1 = sentences.get(i);
                Sentence sent2 = sentences.get(j);
                if (sent1.getNote().getOwner() != sent2.getNote().getOwner())
                    comb.add(new Sentence[] { sent1, sent2 });
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
