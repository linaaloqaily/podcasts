package com.example.podcasts.Repository;

import com.example.podcasts.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {

    Comment findCommentsByUserId(Integer id);
}
