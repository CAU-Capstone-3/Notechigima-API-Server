package com.capstone.notechigima.controller;

import com.capstone.notechigima.config.BaseException;
import com.capstone.notechigima.config.BaseResponse;
import com.capstone.notechigima.config.BaseResponseStatus;
import com.capstone.notechigima.dto.note.PostNoteRequestDTO;
import com.capstone.notechigima.dto.sentence.SentenceListGetResponseDTO;
import com.capstone.notechigima.service.NoteServiceJPA;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "note", description = "노트 API")
@RestController
@RequestMapping("/api/note")
@RequiredArgsConstructor
public class NoteController {

    private final NoteServiceJPA noteService;

    @ResponseBody
    @GetMapping(value="/{noteId}")
    @Operation(summary = "노트 상세조회", description = "노트 ID로 노트의 상세 내용을 조회")
    public BaseResponse<List<SentenceListGetResponseDTO>> getNote(@PathVariable("noteId") int noteId) throws BaseException {
        return new BaseResponse(BaseResponseStatus.SUCCESS_READ, noteService.getNote(noteId));
    }

    @PostMapping
    @Operation(summary = "노트 작성", description = "해당 토픽에 노트 작성")
    public BaseResponse postNote(@RequestBody PostNoteRequestDTO body) throws BaseException {
        noteService.postNote(body);
        return new BaseResponse(BaseResponseStatus.SUCCESS_WRITE);
    }
}
