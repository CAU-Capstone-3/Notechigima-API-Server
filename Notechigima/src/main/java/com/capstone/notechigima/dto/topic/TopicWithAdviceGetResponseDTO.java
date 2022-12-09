package com.capstone.notechigima.dto.topic;

import com.capstone.notechigima.dto.advice.AdviceGetDTO;
import com.capstone.notechigima.dto.advice.AdviceGetResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class TopicWithAdviceGetResponseDTO {
    @Schema(description = "토픽 ID", defaultValue = "23")
    private int topicId;
    @Schema(description = "토픽명", defaultValue = "2-3. 프레이밍")
    private String title;
    private List<AdviceGetDTO> advices;

    @Builder
    public TopicWithAdviceGetResponseDTO(int topicId, String title, List<AdviceGetDTO> advices) {
        this.topicId = topicId;
        this.title = title;
        this.advices = advices;
    }
}
