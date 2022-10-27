package com.capstone.notechigima.controller;

import com.capstone.notechigima.config.BaseException;
import com.capstone.notechigima.config.ResponseEntity;
import com.capstone.notechigima.config.BaseResponseStatus;
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
    @GetMapping(value="/{noteId}")
    public ResponseEntity<List<SentenceResponseDTO>> getTest(@PathVariable("noteId") int noteId) throws BaseException {
        return new ResponseEntity(BaseResponseStatus.SUCCESS_READ, noteService.getNote(noteId));

    }
}
