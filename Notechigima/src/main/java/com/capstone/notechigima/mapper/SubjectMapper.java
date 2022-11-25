package com.capstone.notechigima.mapper;

import com.capstone.notechigima.domain.VisibilityStatus;
import com.capstone.notechigima.domain.study_group.StudyGroup;
import com.capstone.notechigima.domain.subject.Subject;
import com.capstone.notechigima.dto.subject.SubjectGetResponseDTO;
import com.capstone.notechigima.dto.subject.SubjectPostRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubjectMapper {
    SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);

    SubjectGetResponseDTO toSubjectGetResponseDTO(Subject entity);

    default Subject toEntity(SubjectPostRequestDTO dto, StudyGroup studyGroup) {
        return Subject.builder()
                .name(dto.getSubjectName())
                .status(VisibilityStatus.VISIBLE)
                .studyGroup(studyGroup)
                .build();
    }
}
