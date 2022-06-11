package com.example.podcasts.Repository;

import com.example.podcasts.Model.Comment;
import com.example.podcasts.Model.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {

    List<Comment> findAllByUserId(Integer id);
}
