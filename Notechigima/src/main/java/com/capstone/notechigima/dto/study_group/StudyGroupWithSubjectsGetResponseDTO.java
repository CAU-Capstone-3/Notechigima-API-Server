package com.capstone.notechigima.dto.study_group;

import com.capstone.notechigima.dto.subject.SubjectGetResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Getter
@NoArgsConstructor
public class StudyGroupWithSubjectsGetResponseDTO {

    @Schema(description = "스터디 그룹 ID", defaultValue = "1")
    private int groupId;
    @Schema(description = "스터디 그룹명", defaultValue = "캡스톤3조")
    private String groupName;

    @Schema(description = "스터디 그룹장 ID", defaultValue = "2")
    private int groupOwnerId;

    @Schema(description = "스터디 그룹장 이름", defaultValue = "장훈석")
    private String groupOwnerName;

    @Schema(description = "과목 목록")
    private List<SubjectGetResponseDTO> subjects;

    @Builder
    public StudyGroupWithSubjectsGetResponseDTO(int groupId, String groupName, int groupOwnerId, String groupOwnerName, List<SubjectGetResponseDTO> subjects) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupOwnerId = groupOwnerId;
        this.groupOwnerName = groupOwnerName;
        this.subjects = subjects;
    }
}
