package com.capstone.notechigima.dto.group;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetGroupResponseDTO {
    @Schema(description = "스터디 그룹 ID", defaultValue = "1")
    private int groupId;
    @Schema(description = "스터디 그룹명", defaultValue = "캡스톤3조")
    private String groupName;
}
