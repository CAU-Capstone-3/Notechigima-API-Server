package com.capstone.notechigima.repository;

import com.capstone.notechigima.domain.comment.Comment;
import com.capstone.notechigima.domain.comment.CommentDetailEntity;
import com.capstone.notechigima.domain.comment.CommentWriteEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
