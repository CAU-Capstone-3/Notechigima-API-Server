package com.capstone.notechigima.service;

import com.capstone.notechigima.config.ExceptionCode;
import com.capstone.notechigima.config.RestApiException;
import com.capstone.notechigima.domain.VisibilityStatus;
import com.capstone.notechigima.domain.advice.Advice;
import com.capstone.notechigima.domain.advice_sentence.AdviceSentence;
import com.capstone.notechigima.domain.analysis.*;
import com.capstone.notechigima.domain.group_member.GroupAccessType;
import com.capstone.notechigima.domain.group_member.GroupMember;
import com.capstone.notechigima.domain.note.Note;
import com.capstone.notechigima.domain.subject.Subject;
import com.capstone.notechigima.domain.topic.Topic;
import com.capstone.notechigima.domain.topic.TopicAnalyzedType;
import com.capstone.notechigima.domain.users.User;
import com.capstone.notechigima.dto.subject.SubjectWithTopicsGetResponseDTO;
import com.capstone.notechigima.dto.topic.TopicGetResponseDTO;
import com.capstone.notechigima.dto.topic.TopicPostRequestDTO;
import com.capstone.notechigima.dto.users.UserNicknameGetResponseDTO;
import com.capstone.notechigima.mapper.TopicMapper;
import com.capstone.notechigima.mapper.UserMapper;
import com.capstone.notechigima.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TopicService {

    @Value("${nli.inference.uri}")
    private String NLI_ULI;

    private final SubjectRepository subjectRepository;
    private final TopicRepository topicRepository;
    private final AdviceRepository adviceRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final NoteRepository noteRepository;
    private final AdviceSentenceRepository adviceSentenceRepository;

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
    @Async("threadPoolTaskExecutor")
    public void requestAnalysis(int topicId) throws RestApiException {
        Topic topicToUpdate = topicRepository.findById(topicId).orElseThrow(() -> {
            throw new RestApiException(ExceptionCode.ERROR_NOT_FOUND_RESOURCE);
        });
        topicToUpdate.updateAnalyzed(TopicAnalyzedType.RUNNING);
        topicRepository.save(topicToUpdate);

        User owner = groupMemberRepository.findAllByStudyGroup_GroupId(
                topicToUpdate.getSubject().getStudyGroup().getGroupId())
                .stream()
                .filter(member -> member.getAccess() == GroupAccessType.OWNER)
                .findFirst()
                .map(GroupMember::getUser)
                .orElseThrow(() -> {
                    throw new RestApiException(ExceptionCode.ERROR_NOT_FOUND_USER);
                });


        List<Note> notes = topicToUpdate.getNotes();
        MergedDocument mergedDocument = analysisTopic(notes, owner.getUserId());
        mergedDocument.getSentences()
                        .forEach(paragraph -> {
                            saveMergedParagraph(topicToUpdate, paragraph);
                        });

        topicToUpdate.updateAnalyzed(TopicAnalyzedType.FINISH);
        topicRepository.save(topicToUpdate);
    }

    private void saveMergedParagraph(Topic topic, MergedSentence mergedSentence) {
        String content;

        if (mergedSentence.isContradiction()) {
            content = "from 모두, 상반된 문장이 있어요.";
        }
        else if (mergedSentence.isSuccess()) {
            content = "";
        }
        else {
            StringBuilder sb = new StringBuilder();

            for (MergedSentence.Omission omission : mergedSentence.getOmissions()) {
                if (omission.getKeywords().isEmpty())
                    continue;

                User writer = noteRepository.findById(omission.getNoteId())
                        .orElseThrow(() -> {
                            throw new RestApiException(ExceptionCode.ERROR_NOT_FOUND_USER);
                        })
                        .getOwner();

                sb.append("from ")
                        .append(writer.getNickname())
                        .append(", ");
                for (int i = 0; i < omission.getKeywords().size(); i++) {
                    if (i != 0)
                        sb.append(", ");
                    sb.append(omission.getKeywords().get(i));
                }
                sb.append(" 에 대한 내용이 빠져있어요.\n");
            }
            content = sb.toString();
        }

        Advice advice = Advice.builder()
                .topic(topic)
                .content(content)
                .build();

        for (int i = 0; i < mergedSentence.getRepresent().size(); i++) {
            boolean represent = false;
            if (i == 0) represent = true;
            if (mergedSentence.isContradiction()) represent = true;

            MergedSentence.Sentence s = mergedSentence.getRepresent().get(i);

            AdviceSentence adviceSentence = AdviceSentence.builder()
                    .advice(advice)
                    .content(s.getContent())
                    .represent(represent)
                    .build();
            adviceSentenceRepository.save(adviceSentence);

        }
        adviceRepository.save(advice);
    }

    private MergedDocument analysisTopic(List<Note> notes, int groupOwnerId) {
        DocumentParser parser = new DocumentParser();

        // analysis
        Document centralDocument = null;
        List<Document> documents = new ArrayList<>();

        for (Note note : notes) {
            Document document = parser.parse(note.getNoteId(), note.getContent());

            if (groupOwnerId == note.getOwner().getUserId()) {
                centralDocument = document;
            } else {
                documents.add(document);
            }
        }

        // 여러명의 문단을 묶어서 초기화한다.
        DocumentAnalyzer analyzer = new DocumentAnalyzer(NLI_ULI, centralDocument, documents);
        return analyzer.merge();
    }

}
