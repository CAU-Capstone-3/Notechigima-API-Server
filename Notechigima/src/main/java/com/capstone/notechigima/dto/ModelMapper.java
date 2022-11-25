package com.capstone.notechigima.dto;

import com.capstone.notechigima.domain.study_group.StudyGroup;
import com.capstone.notechigima.domain.topic.Topic;
import com.capstone.notechigima.domain.users.User;
import com.capstone.notechigima.dto.study_group.StudyGroupGetResponseDTO;
import com.capstone.notechigima.dto.sentence.SentenceGetResponseDTO;
import com.capstone.notechigima.dto.topic.TopicResponseDTO;
import com.capstone.notechigima.dto.users.UserGetResponseDTO;


public class ModelMapper {

    public SentenceGetResponseDTO map(int sentenceId, String content, int writerId, String writerName) {
        return new SentenceGetResponseDTO(
                sentenceId,
                content,
                writerId,
                writerName
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
