package com.capstone.notechigima.dto.study_group;

import com.capstone.notechigima.dto.subject.SubjectGetResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class StudyGroupWithSubjectsGetResponseDTO {

    @Schema(description = "스터디 그룹 ID", defaultValue = "1")
    private int groupId;
    @Schema(description = "스터디 그룹명", defaultValue = "캡스톤3조")
    private String groupName;
    @Schema(description = "과목 목록")
    private List<SubjectGetResponseDTO> subjects;

    @Builder
    public StudyGroupWithSubjectsGetResponseDTO(int groupId, String groupName, List<SubjectGetResponseDTO> subjects) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.subjects = subjects;
    }
}
