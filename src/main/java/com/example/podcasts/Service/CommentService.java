package com.example.podcasts.Service;

import com.example.podcasts.Exception.ApiRequestException;
import com.example.podcasts.Model.Comment;
import com.example.podcasts.Model.PlayList;
import com.example.podcasts.Model.Podcast;
import com.example.podcasts.Model.User;
import com.example.podcasts.Repository.CommentRepository;
import com.example.podcasts.Repository.PodcastRepository;
import com.example.podcasts.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PodcastRepository podcastRepository;


    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    public List<Comment> getCommentByUserId(Integer id) {
      List<Comment> comments = commentRepository.findAllByUserId(id);
      return comments;
    }

    public void addComment(Integer userId ,Integer podcastId ,Comment comment) {
        User user = userRepository.findById(userId).orElse(null);
        if(user == null) {
            throw new ApiRequestException("This user doesn't exist");
        }
        Podcast podcast = podcastRepository.findById(podcastId).orElse(null);
        if(podcast == null) {
            throw new ApiRequestException("This podcast doesn't exist");
        }
//        podcast.getComments().add(comment);
        comment.setUser(user);
        comment.setPodcast(podcast);
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
