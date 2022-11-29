package com.capstone.notechigima.dto.topic;

import com.capstone.notechigima.dto.note.NoteListGetResponseDTO;
import com.capstone.notechigima.dto.users.UserNicknameGetResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class TopicWithNoteListGetResponseDTO {

    @Schema(description = "토픽 ID", defaultValue = "2")
    private int topicId;
    @Schema(description = "토픽명", defaultValue = "2-3. 프레이밍")
    private String topicName;
    @Schema(description = "노트 목록")
    private List<NoteListGetResponseDTO> notes;
    @Schema(description = "노트 미작성자 목록")
    private List<UserNicknameGetResponseDTO> unwrittenUsers;

    @Builder
    public TopicWithNoteListGetResponseDTO(int topicId, String topicName, List<NoteListGetResponseDTO> notes, List<UserNicknameGetResponseDTO> unwrittenUsers) {
        this.topicId = topicId;
        this.topicName = topicName;
        this.notes = notes;
        this.unwrittenUsers = unwrittenUsers;
    }
}
