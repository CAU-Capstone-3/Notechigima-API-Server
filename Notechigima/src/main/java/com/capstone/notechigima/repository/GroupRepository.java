package com.capstone.notechigima.repository;

import com.capstone.notechigima.domain.study_group.GroupCreateEntity;
import com.capstone.notechigima.domain.study_group.GroupEntity;
import com.capstone.notechigima.domain.users.MemberEntity;
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
