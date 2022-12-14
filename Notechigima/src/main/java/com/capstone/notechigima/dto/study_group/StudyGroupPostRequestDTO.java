package com.capstone.notechigima.dto.study_group;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StudyGroupPostRequestDTO {
    @Schema(description = "현재 사용자 ID", defaultValue = "3")
    private int userId;
    @Schema(description = "그룹명", defaultValue = "캡스톤3조")
    private String groupName;
}
