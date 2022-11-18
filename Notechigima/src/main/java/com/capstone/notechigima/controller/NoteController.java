package com.capstone.notechigima.controller;

import com.capstone.notechigima.config.BaseException;
import com.capstone.notechigima.config.BaseResponse;
import com.capstone.notechigima.config.BaseResponseStatus;
import com.capstone.notechigima.model.dto.note.GetNoteSummarizedDTO;
import com.capstone.notechigima.model.dto.note.PostNoteRequestDTO;
import com.capstone.notechigima.model.dto.sentence.SentenceResponseDTO;
import com.capstone.notechigima.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "note", description = "노트 API")
@RestController
@RequestMapping("/api/note")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @ResponseBody
    @GetMapping("/topic/{topicId}")
    @Operation(summary = "토픽별 노트 목록", description = "해당 토픽 내에서 작성된 노트의 목록")
    public BaseResponse<List<GetNoteSummarizedDTO>> getNoteList(@PathVariable("topicId") int topicId) throws BaseException {
        return new BaseResponse(BaseResponseStatus.SUCCESS_READ, noteService.getNoteList(topicId));
    }

    @ResponseBody
    @GetMapping(value="/{noteId}")
    @Operation(summary = "노트 상세조회", description = "노트 ID로 노트의 상세 내용을 조회")
    public BaseResponse<List<SentenceResponseDTO>> getNote(@PathVariable("noteId") int noteId) throws BaseException {
        return new BaseResponse(BaseResponseStatus.SUCCESS_READ, noteService.getNote(noteId));
    }

    @PostMapping("/{topicId}")
    @Operation(summary = "노트 작성", description = "해당 토픽에 노트 작성")
    public BaseResponse postNote(@PathVariable("topicId") int topicId, @RequestBody PostNoteRequestDTO body) throws BaseException {
        noteService.postNote(topicId, body);
        return new BaseResponse(BaseResponseStatus.SUCCESS_EDIT);
    }
}
