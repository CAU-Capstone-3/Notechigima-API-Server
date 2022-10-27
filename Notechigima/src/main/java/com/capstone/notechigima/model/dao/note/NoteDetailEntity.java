package com.capstone.notechigima.model.dao.note;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class NoteDetailEntity {
    private int noteId;
    private int ownerId;
    private int sectionId;
    private char status;
    private Date createdAt;
    private Date updatedAt;
    private String ownerName;
    private String sectionName;
    private int subjectId;
    private String subjectName;
}
