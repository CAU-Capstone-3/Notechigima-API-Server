package com.capstone.notechigima.repository;

import com.capstone.notechigima.domain.study_group.GroupCreateEntity;
import com.capstone.notechigima.domain.study_group.GroupEntity;
import com.capstone.notechigima.domain.study_group.StudyGroup;
import com.capstone.notechigima.domain.users.MemberEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface GroupRepository extends JpaRepository<StudyGroup, Integer> {

}
