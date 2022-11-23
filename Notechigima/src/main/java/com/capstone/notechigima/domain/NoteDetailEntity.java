package com.capstone.notechigima.domain;

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
    private int topicId;
    private char status;
    private Date createdAt;
    private Date updatedAt;
    private String ownerName;
    private String topicName;
    private int subjectId;
    private String subjectName;
}
