package com.capstone.notechigima.mapper;

import com.capstone.notechigima.domain.group_member.GroupMember;
import com.capstone.notechigima.domain.study_group.StudyGroup;
import com.capstone.notechigima.dto.study_group.StudyGroupGetResponseDTO;
import com.capstone.notechigima.dto.study_group.StudyGroupPostRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudyGroupMapper extends GenericMapper<StudyGroup, StudyGroupGetResponseDTO> {
    StudyGroupMapper INSTANCE = Mappers.getMapper(StudyGroupMapper.class);

    default StudyGroupGetResponseDTO toStudyGroupGetResponseDTO(GroupMember entity) {
        return StudyGroupGetResponseDTO.builder()
                .groupId(entity.getStudyGroup().getGroupId())
                .groupName(entity.getStudyGroup().getName())
                .build();
    }

}

