package com.capstone.notechigima.repository;

import com.capstone.notechigima.domain.invite.GroupInvite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupInviteRepository extends JpaRepository<GroupInvite, Integer> {
}
