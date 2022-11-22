package com.capstone.notechigima.model.dao.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentWriteEntity {
    private int userId;
    private int adviceId;
    private String content;
}
