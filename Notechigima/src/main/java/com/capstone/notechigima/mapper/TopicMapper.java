package com.capstone.notechigima.mapper;

import com.capstone.notechigima.domain.topic.Topic;
import com.capstone.notechigima.dto.topic.TopicGetResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TopicMapper {
    TopicMapper INSTANCE = Mappers.getMapper(TopicMapper.class);

    TopicGetResponseDTO toTopicGetResponseDTO(Topic entity);
}
