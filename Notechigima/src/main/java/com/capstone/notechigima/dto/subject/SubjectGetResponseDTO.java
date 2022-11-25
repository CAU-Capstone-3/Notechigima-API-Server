package com.capstone.notechigima.dto.subject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SubjectGetResponseDTO {
    @Schema(description = "과목 ID", defaultValue = "1")
    private int subjectId;
    @Schema(description = "과목명", defaultValue = "데이터베이스설계")
    private String name;

    @Builder
    public SubjectGetResponseDTO(int subjectId, String name) {
        this.subjectId = subjectId;
        this.name = name;
    }
}
