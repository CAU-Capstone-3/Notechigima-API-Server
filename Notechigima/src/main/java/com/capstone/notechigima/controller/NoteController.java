package com.capstone.notechigima.controller;

import com.capstone.notechigima.config.BaseResponse;
import com.capstone.notechigima.config.SuccessCode;
import com.capstone.notechigima.dto.note.NotePostRequestDTO;
import com.capstone.notechigima.dto.sentence.SentenceListGetResponseDTO;
import com.capstone.notechigima.service.AuthService;
import com.capstone.notechigima.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.AccessException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static com.capstone.notechigima.config.jwt.JwtUtils.ACCESS_TOKEN_HEADER;

@Tag(name = "note", description = "노트 API")
@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;
    private final AuthService authService;

    @ResponseBody
    @GetMapping(value="/{noteId}")
    @Operation(summary = "노트 상세조회", description = "노트 ID로 노트의 상세 내용을 조회")
    public BaseResponse<List<SentenceListGetResponseDTO>> getNote(
            HttpServletRequest request,
            @PathVariable("noteId") int noteId
            ) throws AccessException {

        authService.authorizationByNoteId(request.getHeader(ACCESS_TOKEN_HEADER), noteId);
        return new BaseResponse(SuccessCode.SUCCESS_READ, noteService.getNote(noteId));
    }

    @PostMapping
    @Operation(summary = "노트 작성", description = "해당 토픽에 노트 작성")
    public BaseResponse postNote(
            HttpServletRequest request,
            @RequestBody NotePostRequestDTO body) throws AccessException {

        authService.authorizationByTopicId(request.getHeader(ACCESS_TOKEN_HEADER), body.getTopicId());

        noteService.postNote(body);
        return new BaseResponse(SuccessCode.SUCCESS_WRITE);
    }
}
