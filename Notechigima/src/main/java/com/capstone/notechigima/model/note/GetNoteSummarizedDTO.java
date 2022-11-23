package com.capstone.notechigima.model.note;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class GetNoteSummarizedDTO {
    @Schema(description = "노트 ID", defaultValue = "5")
    private int noteId;
    @Schema(description = "노트 작성자 ID", defaultValue = "1")
    private int ownerId;
    @Schema(description = "노트 작성자명", defaultValue = "김형기")
    private String ownerName;
    @Schema(description = "노트 최종 업데이트 시간")
    private Date updatedAt;
}
