package com.capstone.notechigima.model.dto.note;

import com.capstone.notechigima.model.dto.advice.AdviceResponseDTO;
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
    private int sectionId;
    private String sectionName;
    private int noteId;
    private int writerId;
    private String writerName;
    private List<SentenceResponseDTO> sentenceList;
    private List<AdviceResponseDTO> adviceList;
    private Date lastUpdate;
}
