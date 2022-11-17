package com.capstone.notechigima.model;

import com.capstone.notechigima.model.dao.advice.AdviceDetailEntity;
import com.capstone.notechigima.model.dao.comment.CommentEntity;
import com.capstone.notechigima.model.dao.note.NoteDetailEntity;
import com.capstone.notechigima.model.dao.note.NoteOwnerEntity;
import com.capstone.notechigima.model.dao.topic.TopicEntity;
import com.capstone.notechigima.model.dao.sentence.SentenceEntity;
import com.capstone.notechigima.model.dto.advice.AdviceResponseDTO;
import com.capstone.notechigima.model.dto.note.GetNoteResponseDTO;
import com.capstone.notechigima.model.dto.note.GetNoteSummarizedDTO;
import com.capstone.notechigima.model.dto.topic.TopicResponseDTO;
import com.capstone.notechigima.model.dto.sentence.SentenceResponseDTO;

import java.util.List;

public class ModelMapper {

    public SentenceResponseDTO map(SentenceEntity entity) {
        return new SentenceResponseDTO(entity.getContent(), entity.getSequenceNum(), entity.getSentenceType());
    }

    public GetNoteSummarizedDTO map(NoteOwnerEntity entity) {
        return new GetNoteSummarizedDTO(entity.getNoteId(), entity.getOwnerId(), entity.getOwnerName(), entity.getUpdatedAt());
    }

    public TopicResponseDTO map(TopicEntity entity) {
        return new TopicResponseDTO(entity.getTopicId(), entity.getTitle(), entity.getUpdatedAt(), entity.isAnalyzed());
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
                entity.getAdviceType() == 'D' ? "함께 다시 확인해야할 문장이예요." : "",
                entity.getSentenceId(),
                entity.getSentence(),
                entity.getWriterId(),
                entity.getWriterName(),
                entity.getComments()
        );
    }
}
