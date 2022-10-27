package com.capstone.notechigima.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Mapper
public interface AdviceRepository {

    int insertAll(Map<String, Object> map);
}
