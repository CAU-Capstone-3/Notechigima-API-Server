package com.capstone.notechigima.domain.note;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class NoteOwnerEntity {
    private int noteId;
    private int ownerId;
    private String ownerName;
    private Date updatedAt;
}
