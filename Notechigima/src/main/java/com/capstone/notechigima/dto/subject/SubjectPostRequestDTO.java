package com.capstone.notechigima.dto.subject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SubjectPostRequestDTO {
    @Schema(description = "그룹 ID", defaultValue = "1")
    private int groupId;
    @Schema(description = "과목 이름", defaultValue = "운영체제")
    private String subjectName;

    @Builder
    public SubjectPostRequestDTO(int groupId, String subjectName) {
        this.groupId = groupId;
        this.subjectName = subjectName;
    }
}
