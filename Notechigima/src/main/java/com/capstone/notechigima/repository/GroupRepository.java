package com.capstone.notechigima.repository;

import com.capstone.notechigima.model.dao.group.GroupCreateEntity;
import com.capstone.notechigima.model.dao.group.GroupEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GroupRepository {
    List<GroupEntity> getGroups(int userId);
    int insertGroup(GroupCreateEntity body);
}
