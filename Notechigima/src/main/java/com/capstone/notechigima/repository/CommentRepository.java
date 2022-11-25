package com.capstone.notechigima.repository;

import com.capstone.notechigima.domain.comment.Comment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @EntityGraph(attributePaths = {"advice"})
    List<Comment> findAllByAdvice_AdviceId(int adviceId);
}
