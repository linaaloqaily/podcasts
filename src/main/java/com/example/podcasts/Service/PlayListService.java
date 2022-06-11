package com.example.podcasts.Service;


import com.example.podcasts.Exception.ApiRequestException;
import com.example.podcasts.Model.PlayList;
import com.example.podcasts.Model.Podcast;
import com.example.podcasts.Model.User;
import com.example.podcasts.Repository.PlayListRepository;
import com.example.podcasts.Repository.PodcastRepository;
import com.example.podcasts.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayListService {

    private final PlayListRepository playListRepository;
    private final PodcastRepository podcastRepository;
    private final UserRepository userRepository;


    public void addPlayListToUser(Integer id,PlayList playList) throws IOException {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) {
            throw new ApiRequestException("This user doesn't exist");
        }
        playList.setUser(user);
        playListRepository.save(playList);
    }


    public List<PlayList> getPlayListByUserId(Integer id) {
    List<PlayList> playList = playListRepository.findAllByUserId(id);
        return playList;
    }


    public boolean deletePlayList(Integer id) {
        Optional<PlayList> PlayList = playListRepository.findById(id);
        if (!PlayList.isPresent()){
            return false;
        }
        playListRepository.deleteById(id);
        return true;
    }

    public Object getAllPlayList() {
        return playListRepository.findAll();
    }

    public PlayList addPodcastToPlayList(Integer podcastId, Integer playListId) {
        Podcast podcast = podcastRepository.findById(podcastId).orElse(null);
        if(playListId == null) {
            throw new ApiRequestException("This podcast doesn't exist");
        }
        PlayList playList = playListRepository.findById(playListId).orElse(null);
        if(playList == null) {
            throw new ApiRequestException("This playList doesn't exist");
        }
        playList.getPodcast().add(podcast);

       return playListRepository.save(playList);
    }
}
