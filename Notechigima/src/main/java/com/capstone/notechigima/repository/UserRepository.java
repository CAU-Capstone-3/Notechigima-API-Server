package com.capstone.notechigima.repository;

import com.capstone.notechigima.domain.users.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

//    List<UserEntity> getMembersByGroupId(int groupId);

}
