package com.example.podcasts.Service;

import com.example.podcasts.Model.Comment;
import com.example.podcasts.Repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment getCommentByUserId(Integer id) {
      return commentRepository.findCommentsByUserId(id);
    }

    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }


    public boolean deleteComment(Integer id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (!comment.isPresent()){
            return false;
        }
        commentRepository.deleteById(id);
        return true;
    }
}
