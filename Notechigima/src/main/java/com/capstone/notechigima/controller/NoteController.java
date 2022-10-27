package com.capstone.notechigima.controller;

import com.capstone.notechigima.config.BaseResponse;
import com.capstone.notechigima.config.BaseResponseStatus;
import com.capstone.notechigima.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @ResponseBody
    @GetMapping(value="/{noteId}")
    public BaseResponse<String> getTest(@PathVariable("noteId") int noteId) {
        return new BaseResponse(BaseResponseStatus.SUCCESS_READ, Integer.toString(noteId));
    }

}
