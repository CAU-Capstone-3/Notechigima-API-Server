package com.capstone.notechigima.model.dto.note;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class GetNoteSummarizedDTO {
    private int noteId;
    private int ownerId;
    private String ownerName;
    private Date updatedAt;
}
