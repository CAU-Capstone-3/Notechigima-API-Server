package com.capstone.notechigima.controller;

import com.capstone.notechigima.config.BaseException;
import com.capstone.notechigima.config.BaseResponse;
import com.capstone.notechigima.config.BaseResponseStatus;
import com.capstone.notechigima.model.dto.note.GetNoteSummarizedDTO;
import com.capstone.notechigima.model.dto.note.PostNoteRequestDTO;
import com.capstone.notechigima.model.dto.sentence.SentenceResponseDTO;
import com.capstone.notechigima.service.NoteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "note", description = "노트 API")
@RestController
@RequestMapping("/api/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @ResponseBody
    @GetMapping("/topic/{topicId}")
    public BaseResponse<List<GetNoteSummarizedDTO>> getNoteList(@PathVariable("topicId") int topicId) throws BaseException {
        return new BaseResponse(BaseResponseStatus.SUCCESS_READ, noteService.getNoteList(topicId));
    }

    @ResponseBody
    @GetMapping(value="/{noteId}")
    public BaseResponse<List<SentenceResponseDTO>> getNote(@PathVariable("noteId") int noteId) throws BaseException {
        return new BaseResponse(BaseResponseStatus.SUCCESS_READ, noteService.getNote(noteId));
    }

    @PostMapping("/{topicId}")
    public BaseResponse postNote(@PathVariable("topicId") int topicId, @RequestBody PostNoteRequestDTO body) throws BaseException {
        noteService.postNote(topicId, body);
        return new BaseResponse(BaseResponseStatus.SUCCESS_EDIT);
    }
}
