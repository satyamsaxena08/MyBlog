package com.myBlog.controller;

import com.myBlog.payload.CommentDto;
import com.myBlog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    //http://localhost:8080/api/comments?postId=1
    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,
                                                    @RequestParam long postId){
        CommentDto dto = commentService.createComments(commentDto, postId);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
