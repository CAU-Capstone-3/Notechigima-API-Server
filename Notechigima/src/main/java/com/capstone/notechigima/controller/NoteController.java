package com.capstone.notechigima.controller;

import com.capstone.notechigima.config.BaseException;
import com.capstone.notechigima.config.BaseResponse;
import com.capstone.notechigima.config.BaseResponseStatus;
import com.capstone.notechigima.model.dto.note.GetNoteSummarizedDTO;
import com.capstone.notechigima.model.dto.note.PostNoteRequestDTO;
import com.capstone.notechigima.model.dto.sentence.SentenceResponseDTO;
import com.capstone.notechigima.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @ResponseBody
    @GetMapping("/section/{sectionId}")
    public BaseResponse<List<GetNoteSummarizedDTO>> getNoteList(@PathVariable("sectionId") int sectionId) throws BaseException {
        return new BaseResponse(BaseResponseStatus.SUCCESS_READ, noteService.getNoteList(sectionId));
    }

    @ResponseBody
    @GetMapping(value="/{noteId}")
    public BaseResponse<List<SentenceResponseDTO>> getNote(@PathVariable("noteId") int noteId) throws BaseException {
        return new BaseResponse(BaseResponseStatus.SUCCESS_READ, noteService.getNote(noteId));
    }

    @PostMapping("/{sectionId}")
    public BaseResponse postNote(@PathVariable("sectionId") int sectionId, @RequestBody PostNoteRequestDTO body) throws BaseException {
        noteService.postNote(sectionId, body);
        return new BaseResponse(BaseResponseStatus.SUCCESS_EDIT);
    }
}
