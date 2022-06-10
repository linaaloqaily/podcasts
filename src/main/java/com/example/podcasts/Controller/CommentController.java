package com.example.podcasts.Controller;

import com.example.podcasts.Model.Comment;
import com.example.podcasts.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/comment")
public class CommentController {

    private final CommentService commentService;


    //  Endpoint for User

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentByUserId(@PathVariable Integer id){
        return ResponseEntity.status(200).body(commentService.getCommentByUserId(id));
    }

    // ناقص
    @PostMapping
    public ResponseEntity addCommentByUserToPodcast(@Valid @RequestBody Comment comment){
        commentService.addComment(comment);
        return ResponseEntity.status(201).body("Comment Added");
    }


    // Endpoint for both

    @DeleteMapping("/{id}")
    public ResponseEntity deleteComment(@PathVariable Integer id){
        commentService.deleteComment(id);
        return ResponseEntity.status(200).body("Comment deleted");
    }

}
