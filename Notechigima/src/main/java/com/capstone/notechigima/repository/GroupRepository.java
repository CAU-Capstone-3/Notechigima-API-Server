package com.capstone.notechigima.repository;

import com.capstone.notechigima.domain.study_group.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GroupRepository extends JpaRepository<StudyGroup, Integer> {

}
