package com.capstone.notechigima.mapper;

import com.capstone.notechigima.domain.subject.Subject;
import com.capstone.notechigima.dto.subject.SubjectGetResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubjectMapper {
    SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);

    SubjectGetResponseDTO toSubjectGetResponseDTO(Subject entity);
}
