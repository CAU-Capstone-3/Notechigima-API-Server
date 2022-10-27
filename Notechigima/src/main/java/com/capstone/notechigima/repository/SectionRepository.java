package com.capstone.notechigima.repository;

import com.capstone.notechigima.model.dao.section.SectionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SectionRepository {

    public List<SectionEntity> getSectionList(int subjectId);

}
