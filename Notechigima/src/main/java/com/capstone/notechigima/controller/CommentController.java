package com.capstone.notechigima.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "comment", description = "댓글 API")
@RestController
@RequestMapping("/api/comment")
public class CommentController {

}
