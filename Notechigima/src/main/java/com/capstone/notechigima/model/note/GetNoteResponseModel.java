package com.capstone.notechigima.model.note;

import com.capstone.notechigima.model.advice.AdviceResponseModel;
import com.capstone.notechigima.model.sentence.SentenceResponseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class GetNoteResponseModel {
    private int subjectId;
    private String subjectName;
    private int sectionId;
    private String sectionName;
    private int noteId;
    private int writerId;
    private String writerName;
    private List<SentenceResponseModel> sentenceList;
    private List<AdviceResponseModel> adviceList;
    private Date lastUpdate;
}
