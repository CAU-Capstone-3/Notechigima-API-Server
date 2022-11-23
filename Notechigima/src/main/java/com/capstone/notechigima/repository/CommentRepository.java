package com.capstone.notechigima.repository;

import com.capstone.notechigima.domain.comment.CommentDetailEntity;
import com.capstone.notechigima.domain.comment.CommentWriteEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentRepository {

    List<CommentDetailEntity> getComments();

    int postComment(CommentWriteEntity entity);
}
