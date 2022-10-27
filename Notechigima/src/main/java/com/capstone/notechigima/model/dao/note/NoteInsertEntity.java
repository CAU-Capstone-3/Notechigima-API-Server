package com.capstone.notechigima.model.dao.note;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NoteInsertEntity {
    private int id;
    private int ownerId;
    private int sectionId;
}
