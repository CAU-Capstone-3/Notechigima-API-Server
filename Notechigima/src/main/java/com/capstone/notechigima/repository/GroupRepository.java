package com.capstone.notechigima.repository;

import com.capstone.notechigima.domain.GroupCreateEntity;
import com.capstone.notechigima.domain.GroupEntity;
import com.capstone.notechigima.domain.MemberEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GroupRepository {
    List<GroupEntity> getGroupsByUserId(int userId);
    int insertGroup(GroupCreateEntity body);
    int insertMember(MemberEntity entity);
}
