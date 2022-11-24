package com.capstone.notechigima.repository;

import com.capstone.notechigima.domain.group_member.GroupMember;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupMemberRepository extends JpaRepository<GroupMember, Integer> {
    
    @EntityGraph(attributePaths = {"user", "studyGroup"})
    List<GroupMember> findAllByStudyGroup_GroupId(int groupId);

}
