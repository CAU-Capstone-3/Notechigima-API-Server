package com.capstone.notechigima.repository;

import com.capstone.notechigima.domain.subject.Subject;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    @EntityGraph(attributePaths = {"studyGroup"})
    List<Subject> findAllByStudyGroup_GroupId(int groupId);
}
