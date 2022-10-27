package com.capstone.notechigima.repository;

import com.capstone.notechigima.model.dao.sentence.SentenceEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface SentenceRepository {

    List<SentenceEntity> getSentenceList(int noteId);

    int insertAll(Map<String, Object> map);
}