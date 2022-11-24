package com.capstone.notechigima.repository;

import com.capstone.notechigima.domain.group_member.GroupMember;
import com.capstone.notechigima.domain.users.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupMemberRepository extends JpaRepository<GroupMember, Integer> {
    
    @EntityGraph(attributePaths = {"user", "studyGroup"})
    @Query("SELECT m FROM GroupMember m")
    List<GroupMember> findAllEntityByGroupId(int groupId);

}
