package com.capstone.notechigima.repository;

import com.capstone.notechigima.model.dao.advice.AdviceDetailEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface AdviceRepository {

    int insertAll(Map<String, Object> map);

    List<AdviceDetailEntity> getAdviceList(int sectionId);
}