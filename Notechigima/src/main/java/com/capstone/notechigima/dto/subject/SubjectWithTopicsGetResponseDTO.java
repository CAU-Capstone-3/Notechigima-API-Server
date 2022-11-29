package com.capstone.notechigima.dto.subject;

import com.capstone.notechigima.dto.topic.TopicGetResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SubjectWithTopicsGetResponseDTO {
    @Schema(description = "과목 ID", defaultValue = "1")
    private int subjectId;
    @Schema(description = "과목명", defaultValue = "컴퓨터통신")
    private String subjectName;
    @Schema(description = "토픽 목록")
    private List<TopicGetResponseDTO> topics;

    @Builder
    public SubjectWithTopicsGetResponseDTO(int subjectId, String subjectName, List<TopicGetResponseDTO> topics) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.topics = topics;
    }
}
