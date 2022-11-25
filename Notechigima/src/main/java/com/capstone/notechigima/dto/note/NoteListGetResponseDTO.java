package com.capstone.notechigima.dto.note;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
public class NoteListGetResponseDTO {
    @Schema(description = "노트 ID", defaultValue = "5")
    private int noteId;
    @Schema(description = "노트 작성자 ID", defaultValue = "1")
    private int ownerId;
    @Schema(description = "노트 작성자명", defaultValue = "김형기")
    private String ownerName;
    @Schema(description = "노트 최종 업데이트 시간")
    private LocalDateTime updatedAt;

    @Builder
    public NoteListGetResponseDTO(int noteId, int ownerId, String ownerName, LocalDateTime updatedAt) {
        this.noteId = noteId;
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.updatedAt = updatedAt;
    }
}
