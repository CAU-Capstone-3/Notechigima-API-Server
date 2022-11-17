package com.capstone.notechigima.repository;

import com.capstone.notechigima.model.dao.comment.CommentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentRepository {

    List<CommentEntity> getComments();
}
