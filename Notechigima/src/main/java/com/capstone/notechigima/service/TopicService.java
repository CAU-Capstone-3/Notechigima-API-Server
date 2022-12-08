package com.capstone.notechigima.service;

import com.capstone.notechigima.config.ExceptionCode;
import com.capstone.notechigima.config.RestApiException;
import com.capstone.notechigima.domain.VisibilityStatus;
import com.capstone.notechigima.domain.group_member.GroupMember;
import com.capstone.notechigima.domain.note.Note;
import com.capstone.notechigima.domain.sentence.Sentence;
import com.capstone.notechigima.domain.sentence_advice.Advice;
import com.capstone.notechigima.domain.sentence_advice.AdviceType;
import com.capstone.notechigima.domain.subject.Subject;
import com.capstone.notechigima.domain.topic.Topic;
import com.capstone.notechigima.domain.topic.TopicAnalyzedType;
import com.capstone.notechigima.domain.users.User;
import com.capstone.notechigima.dto.advice.KeywordInferenceRequestVO;
import com.capstone.notechigima.dto.advice.NliInferenceRequestVO;
import com.capstone.notechigima.dto.subject.SubjectWithTopicsGetResponseDTO;
import com.capstone.notechigima.dto.topic.TopicGetResponseDTO;
import com.capstone.notechigima.dto.topic.TopicPostRequestDTO;
import com.capstone.notechigima.dto.users.UserNicknameGetResponseDTO;
import com.capstone.notechigima.mapper.TopicMapper;
import com.capstone.notechigima.mapper.UserMapper;
import com.capstone.notechigima.repository.*;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${nli.inference.uri}")
    private String NLI_ULI;
    public static final String POSTFIX_URL_NLI = "/nli";
    public static final String POSTFIX_URL_KEYWORD = "/keyword";
    public static final int KEYWORD_TOP_N = 5;
    public static final double KEYWORD_DIVERSITY = 0.7;

    private final SubjectRepository subjectRepository;
    private final TopicRepository topicRepository;
    private final AdviceRepository adviceRepository;
    private final SentenceRepository sentenceRepository;

    public TopicGetResponseDTO getTopic(int topicId) throws RestApiException {
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> {
            throw new RestApiException(ExceptionCode.ERROR_NOT_FOUND_RESOURCE);
        });
        return TopicMapper.INSTANCE.toTopicGetResponseDTO(topic);
    }

    public int createTopic(TopicPostRequestDTO body) throws RestApiException {
        Subject subject = subjectRepository.findById(body.getSubjectId()).orElseThrow(() -> {
            throw new RestApiException(ExceptionCode.ERROR_NOT_FOUND_RESOURCE);
        });

        Topic newTopic = Topic.builder()
                .subject(subject)
                .title(body.getTitle())
                .status(VisibilityStatus.VISIBLE)
                .analyzed(TopicAnalyzedType.UNREADY)
                .build();

        topicRepository.save(newTopic);
        return newTopic.getTopicId();
    }

    public SubjectWithTopicsGetResponseDTO getTopicListWithSubject(int subjectId) throws RestApiException {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> {
            throw new RestApiException(ExceptionCode.ERROR_NOT_FOUND_RESOURCE);
        });
        List<TopicGetResponseDTO> topics = topicRepository.findAllBySubject_SubjectId(subjectId).stream()
                .map(TopicMapper.INSTANCE::toTopicGetResponseDTO
                ).toList();

        return SubjectWithTopicsGetResponseDTO.builder()
                .subjectId(subjectId)
                .subjectName(subject.getName())
                .topics(topics)
                .build();
    }

    public List<UserNicknameGetResponseDTO> getUnwrittenUsers(int topicId) throws RestApiException {
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> {
            throw new RestApiException(ExceptionCode.ERROR_NOT_FOUND_RESOURCE);
        });
        List<User> groupUsers = topic.getSubject().getStudyGroup().getMembers()
                .stream()
                .map(GroupMember::getUser).toList();

        Set<User> writers = topic.getNotes().stream()
                .map(Note::getOwner)
                .collect(Collectors.toSet());

        List<User> unwrittenUsers = groupUsers.stream()
                .filter(user -> !writers.contains(user))
                .toList();

        return unwrittenUsers.stream()
                .map(UserMapper.INSTANCE::toUserNicknameGetResponseDTO)
                .toList();
    }

    @Transactional
    public void requestAnalysis(int topicId) throws RestApiException {
        Topic topicToUpdate = topicRepository.findById(topicId).orElseThrow(() -> {
            throw new RestApiException(ExceptionCode.ERROR_NOT_FOUND_RESOURCE);
        });
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

    @Async("threadPoolTaskExecutor")
    public boolean isContradiction(String sent1, String sent2) {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = UriComponentsBuilder
                .fromUriString(NLI_ULI + POSTFIX_URL_NLI)
                .encode()
                .build()
                .toUri();
        NliInferenceRequestVO request = new NliInferenceRequestVO(
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

    @Async("threadPoolTaskExecutor")
    public List<String> getKeywords(String document) {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = UriComponentsBuilder
                .fromUriString(NLI_ULI + POSTFIX_URL_KEYWORD)
                .encode()
                .build()
                .toUri();
        KeywordInferenceRequestVO request = KeywordInferenceRequestVO.builder()
                .document(document)
                .topN(KEYWORD_TOP_N)
                .diversity(KEYWORD_DIVERSITY)
                .build();
        String response = restTemplate.postForObject(uri, request, String.class);

        List<String> keywords = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(response);
            for (Object o : jsonObject.getJSONArray("keywords")) {
                keywords.add(o.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return keywords;
    }

}
