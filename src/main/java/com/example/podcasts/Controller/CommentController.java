package com.example.podcasts.Controller;

import com.example.podcasts.Model.Comment;
import com.example.podcasts.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/comment")
public class CommentController {

    Logger logger = LoggerFactory.getLogger(CommentController.class);
    private final CommentService commentService;


    //  Endpoint for User

    @GetMapping("/{id}")
    public ResponseEntity<List<Comment>> getCommentByUserId(@PathVariable Integer id){
        logger.info("Get comment by user id");
        return ResponseEntity.status(200).body(commentService.getCommentByUserId(id));
    }


    @PostMapping("/{userId}/{podcastId}")
    public ResponseEntity addCommentByUserToPodcast(@PathVariable Integer userId,@PathVariable Integer podcastId, @Valid @RequestBody Comment comment){
        commentService.addComment(userId,podcastId,comment);
        logger.info("Add comment to podcast");
        return ResponseEntity.status(201).body("Comment Added");
    }


    //  Endpoint for Admin


    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments(){
        logger.info("Get all comment");
        return ResponseEntity.status(200).body(commentService.getComments());
    }


    // Endpoint for both

    @DeleteMapping("/{id}")
    public ResponseEntity deleteComment(@PathVariable Integer id){
        commentService.deleteComment(id);
        logger.info("Delete comment");
        return ResponseEntity.status(200).body("Comment deleted");
    }
}
