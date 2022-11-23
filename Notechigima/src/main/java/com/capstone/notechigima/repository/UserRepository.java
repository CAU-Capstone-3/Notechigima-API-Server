package com.capstone.notechigima.repository;

import com.capstone.notechigima.domain.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserRepository {
    List<UserEntity> getMembersByGroupId(int groupId);
}
