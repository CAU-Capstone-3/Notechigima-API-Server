package com.capstone.notechigima.model.dto.note;

import com.capstone.notechigima.model.dto.sentence.SentenceResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class GetNoteResponseDTO {
    private int subjectId;
    private String subjectName;
    private int topicId;
    private String topicName;
    private int noteId;
    private int writerId;
    private String writerName;
    private List<SentenceResponseDTO> sentenceList;
    private Date lastUpdate;
}
