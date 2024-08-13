package com.blog.springs.controllers;

import com.blog.springs.payloads.ApiResponse;
import com.blog.springs.payloads.CommentDto;
import com.blog.springs.payloads.UserDto;
import com.blog.springs.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto commentDto, @PathVariable Integer postId){

        CommentDto createdCommentDto=this.commentService.createComment(commentDto,postId);
        return new ResponseEntity<>(createdCommentDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Sucessfully",true), HttpStatus.OK);
    }
}
