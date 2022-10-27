package com.capstone.notechigima.model.dto.note;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostNoteRequestDTO {
    private int writerId;
    private String content;
}
