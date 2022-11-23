package com.capstone.notechigima.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class CommentDetailEntity {
    private int commentId;
    private int userId;
    private String userName;
    private String content;
    private Date createdAt;
    private Date updatedAt;
}
