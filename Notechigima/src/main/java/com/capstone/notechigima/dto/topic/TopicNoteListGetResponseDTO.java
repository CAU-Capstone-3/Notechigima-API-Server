package com.capstone.notechigima.dto.topic;

import com.capstone.notechigima.dto.note.NoteListGetResponseDTO;
import com.capstone.notechigima.dto.users.UserNicknameGetResponseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class TopicNoteListGetResponseDTO {
    private List<NoteListGetResponseDTO> notes;
    private List<UserNicknameGetResponseDTO> unwrittenUsers;

    @Builder
    public TopicNoteListGetResponseDTO(List<NoteListGetResponseDTO> notes, List<UserNicknameGetResponseDTO> unwrittenUsers) {
        this.notes = notes;
        this.unwrittenUsers = unwrittenUsers;
    }
}
