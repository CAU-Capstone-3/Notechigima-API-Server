package com.capstone.notechigima.repository;

import com.capstone.notechigima.domain.group_member.GroupMember;
import com.capstone.notechigima.domain.group_member.GroupMemberPK;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupMemberRepository extends JpaRepository<GroupMember, GroupMemberPK> {

    @EntityGraph(attributePaths = {"user", "studyGroup"})
    List<GroupMember> findAllByStudyGroup_GroupId(int groupId);

    @EntityGraph(attributePaths = {"user", "studyGroup"})
    List<GroupMember> findAllByUser_UserId(int userId);

}
