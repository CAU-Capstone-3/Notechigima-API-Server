package com.capstone.notechigima.model;

import com.capstone.notechigima.model.dao.advice.AdviceDetailEntity;
import com.capstone.notechigima.model.dao.comment.CommentEntity;
import com.capstone.notechigima.model.dao.note.NoteDetailEntity;
import com.capstone.notechigima.model.dao.note.NoteOwnerEntity;
import com.capstone.notechigima.model.dao.sentence.SentenceWithWriterEntity;
import com.capstone.notechigima.model.dao.topic.TopicEntity;
import com.capstone.notechigima.model.dao.sentence.SentenceEntity;
import com.capstone.notechigima.model.dto.advice.AdviceResponseDTO;
import com.capstone.notechigima.model.dto.note.GetNoteResponseDTO;
import com.capstone.notechigima.model.dto.note.GetNoteSummarizedDTO;
import com.capstone.notechigima.model.dto.sentence.SentenceVO;
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
}
