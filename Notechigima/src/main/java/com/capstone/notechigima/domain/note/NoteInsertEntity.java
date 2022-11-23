package com.capstone.notechigima.domain.note;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NoteInsertEntity {
    private int id;
    private int ownerId;
    private int topicId;
}
