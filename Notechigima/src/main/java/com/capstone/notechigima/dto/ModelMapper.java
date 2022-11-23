package com.capstone.notechigima.dto;

import com.capstone.notechigima.domain.advice.AdviceDetailEntity;
import com.capstone.notechigima.domain.group.GroupCreateEntity;
import com.capstone.notechigima.domain.group.GroupEntity;
import com.capstone.notechigima.domain.note.NoteDetailEntity;
import com.capstone.notechigima.domain.note.NoteOwnerEntity;
import com.capstone.notechigima.domain.topic.TopicEntity;
import com.capstone.notechigima.domain.sentence.SentenceEntity;
import com.capstone.notechigima.domain.users.UserEntity;
import com.capstone.notechigima.dto.advice.AdviceResponseDTO;
import com.capstone.notechigima.dto.group.GetGroupResponseDTO;
import com.capstone.notechigima.dto.group.PostGroupRequestDTO;
import com.capstone.notechigima.dto.note.GetNoteResponseDTO;
import com.capstone.notechigima.dto.note.GetNoteSummarizedDTO;
import com.capstone.notechigima.dto.sentence.SentenceVO;
import com.capstone.notechigima.dto.topic.TopicResponseDTO;
import com.capstone.notechigima.dto.sentence.SentenceResponseDTO;
import com.capstone.notechigima.dto.users.GetUserResponseDTO;

import java.util.List;

public class ModelMapper {

    public SentenceResponseDTO map(SentenceEntity entity) {
        return new SentenceResponseDTO(entity.getContent(), entity.getSequenceNum(), entity.getSentenceType());
    }

    public GetNoteSummarizedDTO map(NoteOwnerEntity entity) {
        return new GetNoteSummarizedDTO(entity.getNoteId(), entity.getOwnerId(), entity.getOwnerName(), entity.getUpdatedAt());
    }

    public TopicResponseDTO map(TopicEntity entity) {
        return new TopicResponseDTO(entity.getTopicId(), entity.getTitle(), entity.getUpdatedAt(), entity.getAnalyzed());
    }

    public GetNoteResponseDTO map(NoteDetailEntity entity, List<SentenceResponseDTO> sentenceResult) {
        return new GetNoteResponseDTO(
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

    public AdviceResponseDTO map(AdviceDetailEntity entity) {
        return new AdviceResponseDTO(
                entity.getAdviceId(),
                entity.getAdviceType() == 'C' ? "상반되는 문장이 있어요." : "",
                map(entity.getSentenceId1(), entity.getSentence1(), entity.getWriterId1(), entity.getWriterName1()),
                map(entity.getSentenceId2(), entity.getSentence2(), entity.getWriterId2(), entity.getWriterName2()),
                entity.getComments()
        );
    }

    public SentenceVO map(int sentenceId, String content, int writerId, String writerName) {
        return new SentenceVO(
                sentenceId,
                content,
                writerId,
                writerName
        );
    }

    public GetGroupResponseDTO map(GroupEntity entity) {
        return new GetGroupResponseDTO(
                entity.getGroupId(),
                entity.getGroupName()
        );
    }

    public GroupCreateEntity map(PostGroupRequestDTO body) {
        return new GroupCreateEntity(
                body.getUserId(),
                0,
                body.getGroupName()
        );
    }

    public GetUserResponseDTO map(UserEntity entity) {
        return new GetUserResponseDTO(
                entity.getUserId(),
                entity.getEmail(),
                entity.getNickname()
        );
    }
}
