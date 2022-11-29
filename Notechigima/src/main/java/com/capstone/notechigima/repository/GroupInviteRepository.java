package com.capstone.notechigima.repository;

import com.capstone.notechigima.domain.group_invite.AcceptType;
import com.capstone.notechigima.domain.group_invite.GroupInvite;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupInviteRepository extends JpaRepository<GroupInvite, Integer> {

    @EntityGraph(attributePaths = {"user", "studyGroup"})
    List<GroupInvite> findAllByUser_UserIdAndAccepted(int userId, AcceptType accepted);

    @EntityGraph(attributePaths = {"user", "studyGroup"})
    List<GroupInvite> findAllByStudyGroup_GroupIdAndAccepted(int groupId, AcceptType acceptType);
}
