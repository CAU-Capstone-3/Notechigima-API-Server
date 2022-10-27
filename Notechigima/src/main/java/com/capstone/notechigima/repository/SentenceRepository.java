package com.capstone.notechigima.repository;

import com.capstone.notechigima.model.dao.SentenceEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SentenceRepository {

    List<SentenceEntity> getSentenceList(int noteId);
}
