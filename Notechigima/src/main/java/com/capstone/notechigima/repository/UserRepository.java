package com.capstone.notechigima.repository;

import com.capstone.notechigima.domain.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {

//    List<UserEntity> getMembersByGroupId(int groupId);
    Optional<User> getUserByEmail(String email);

}
