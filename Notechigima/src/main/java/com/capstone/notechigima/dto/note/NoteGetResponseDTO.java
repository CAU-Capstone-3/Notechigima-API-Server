package com.capstone.notechigima.dto.note;

import com.capstone.notechigima.dto.sentence.SentenceListGetResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
public class NoteGetResponseDTO {
    @Schema(description = "노트가 속한 과목 ID", defaultValue = "3")
    private int subjectId;
    @Schema(description = "노트가 속한 과목명", defaultValue = "컴퓨터통신")
    private String subjectName;
    @Schema(description = "노트가 속한 토픽 ID", defaultValue = "2")
    private int topicId;
    @Schema(description = "노트가 속한 토픽명", defaultValue = "2-3. 프레이밍")
    private String topicName;
    @Schema(description = "노트 ID", defaultValue = "4")
    private int noteId;
    @Schema(description = "노트 작성자 ID", defaultValue = "6")
    private int writerId;
    @Schema(description = "노트 작성자명", defaultValue = "장훈석")
    private String writerName;
    @Schema(description = "노트 내 문장 목록")
    private List<SentenceListGetResponseDTO> sentenceList;
    @Schema(description = "노트 최종 업데이트 시간")
    private Date lastUpdate;

    @Builder
    public NoteGetResponseDTO(int subjectId, String subjectName, int topicId, String topicName, int noteId, int writerId, String writerName, List<SentenceListGetResponseDTO> sentenceList, Date lastUpdate) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.topicId = topicId;
        this.topicName = topicName;
        this.noteId = noteId;
        this.writerId = writerId;
        this.writerName = writerName;
        this.sentenceList = sentenceList;
        this.lastUpdate = lastUpdate;
    }
}
