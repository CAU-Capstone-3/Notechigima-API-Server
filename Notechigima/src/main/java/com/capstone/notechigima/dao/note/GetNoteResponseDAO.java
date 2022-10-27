package com.capstone.notechigima.dao.note;

import com.capstone.notechigima.dao.advice.AdviceResponseDAO;
import com.capstone.notechigima.dao.sentence.SentenceResponseDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class GetNoteResponseDAO {
    private int subjectId;
    private String subjectName;
    private int sectionId;
    private String sectionName;
    private int noteId;
    private int writerId;
    private String writerName;
    private List<SentenceResponseDAO> sentenceList;
    private List<AdviceResponseDAO> adviceList;
    private Date lastUpdate;
}
