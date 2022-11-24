package com.capstone.notechigima.dto;

import com.capstone.notechigima.domain.sentence_advice.AdviceDetailEntity;
import com.capstone.notechigima.domain.study_group.GroupEntity;
import com.capstone.notechigima.domain.note.NoteDetailEntity;
import com.capstone.notechigima.domain.note.NoteOwnerEntity;
import com.capstone.notechigima.domain.study_group.StudyGroup;
import com.capstone.notechigima.domain.topic.Topic;
import com.capstone.notechigima.domain.sentence.SentenceEntity;
import com.capstone.notechigima.domain.users.User;
import com.capstone.notechigima.domain.users.UserEntity;
import com.capstone.notechigima.dto.advice.AdviceGetResponseDTO;
import com.capstone.notechigima.dto.study_group.StudyGroupGetResponseDTO;
import com.capstone.notechigima.dto.note.NoteGetResponseDTO;
import com.capstone.notechigima.dto.note.NoteListGetResponseDTO;
import com.capstone.notechigima.dto.sentence.SentenceGetResponseDTO;
import com.capstone.notechigima.dto.topic.TopicResponseDTO;
import com.capstone.notechigima.dto.sentence.SentenceListGetResponseDTO;
import com.capstone.notechigima.dto.users.UserGetResponseDTO;

import java.util.List;

public class ModelMapper {

//    public SentenceListGetResponseDTO map(SentenceEntity entity) {
//        return new SentenceListGetResponseDTO(entity.getContent(), entity.getSequenceNum(), entity.getSentenceType());
//    }

    public NoteListGetResponseDTO map(NoteOwnerEntity entity) {
        return new NoteListGetResponseDTO(entity.getNoteId(), entity.getOwnerId(), entity.getOwnerName(), null);
    }


    public NoteGetResponseDTO map(NoteDetailEntity entity, List<SentenceListGetResponseDTO> sentenceResult) {
        return new NoteGetResponseDTO(
                entity.getSubjectId(),
                entity.getSubjectName(),
                entity.getTopicId(),
                entity.getTopicName(),
                entity.getNoteId(),
                entity.getOwnerId(),
                entity.getOwnerName(),
                sentenceResult,
                entity.getUpdatedAt()
        );
    }

    public AdviceGetResponseDTO map(AdviceDetailEntity entity) {
        return new AdviceGetResponseDTO(
                entity.getAdviceId(),
                entity.getAdviceType() == 'C' ? "상반되는 문장이 있어요." : "",
                map(entity.getSentenceId1(), entity.getSentence1(), entity.getWriterId1(), entity.getWriterName1()),
                map(entity.getSentenceId2(), entity.getSentence2(), entity.getWriterId2(), entity.getWriterName2()),
                entity.getComments()
        );
    }

    public SentenceGetResponseDTO map(int sentenceId, String content, int writerId, String writerName) {
        return new SentenceGetResponseDTO(
                sentenceId,
                content,
                writerId,
                writerName
        );
    }

    public StudyGroupGetResponseDTO map(GroupEntity entity) {
        return new StudyGroupGetResponseDTO(
                entity.getGroupId(),
                entity.getGroupName()
        );
    }


    public UserGetResponseDTO map(UserEntity entity) {
        return new UserGetResponseDTO(
                entity.getUserId(),
                entity.getEmail(),
                entity.getNickname()
        );
    }

    public TopicResponseDTO map(Topic entity) {
        return TopicResponseDTO
                .builder()
                .topicId(entity.getTopicId())
                .title(entity.getTitle())
                .updatedAt(entity.getUpdatedAt())
                .analyzed(entity.getAnalyzed())
                .build();
    }

    public UserGetResponseDTO map(User entity) {
        return UserGetResponseDTO.builder()
                .userId(entity.getUserId())
                .email(entity.getEmail())
                .nickname(entity.getNickname())
                .build();
    }

    public StudyGroupGetResponseDTO map(StudyGroup entity) {
        return StudyGroupGetResponseDTO.builder()
                .groupId(entity.getGroupId())
                .groupName(entity.getName())
                .build();
    }


}
