package com.capstone.notechigima.dto.study_group;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StudyGroupGetResponseDTO {
    @Schema(description = "스터디 그룹 ID", defaultValue = "1")
    private int groupId;
    @Schema(description = "스터디 그룹명", defaultValue = "캡스톤3조")
    private String groupName;

    @Builder
    public StudyGroupGetResponseDTO(int groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }
}
