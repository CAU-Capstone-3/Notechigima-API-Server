package com.capstone.notechigima.controller;

import com.capstone.notechigima.config.BaseException;
import com.capstone.notechigima.config.BaseResponse;
import com.capstone.notechigima.config.BaseResponseStatus;
import com.capstone.notechigima.model.dto.sentence.SentenceResponseDTO;
import com.capstone.notechigima.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "note", description = "노트 API")
@RestController
@RequestMapping("/api/note")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @ResponseBody
    @GetMapping(value="/{noteId}")
    @Operation(summary = "노트 상세조회", description = "노트 ID로 노트의 상세 내용을 조회")
    public BaseResponse<List<SentenceResponseDTO>> getNote(@PathVariable("noteId") int noteId) throws BaseException {
        return new BaseResponse(BaseResponseStatus.SUCCESS_READ, noteService.getNote(noteId));
    }
}
