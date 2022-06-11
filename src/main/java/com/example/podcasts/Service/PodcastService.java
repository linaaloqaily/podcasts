package com.example.podcasts.Service;


import com.example.podcasts.Exception.ApiRequestException;
import com.example.podcasts.Model.Podcast;
import com.example.podcasts.Model.User;
import com.example.podcasts.Repository.PodcastRepository;
import com.example.podcasts.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PodcastService {

    private final PodcastRepository podcastRepository;
    private final UserRepository userRepository;


    public void addPodcastByUser(Integer id, Podcast podcast, MultipartFile sound) throws IOException {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) {
            throw new ApiRequestException("This user doesn't exist");
        }
        String soundType = URLConnection.guessContentTypeFromName(sound.getOriginalFilename());
        if (soundType.endsWith("mp3") || soundType.endsWith("wav") || soundType.endsWith("mpeg")) {
            String folder = "src/main/resources/static/sounds" + id;
            File newDirectory = new File(folder);
            if (!newDirectory.exists()) {
                newDirectory.mkdirs();
            }
            long timeMilli = new Date().getTime();
            String path = timeMilli + sound.getOriginalFilename().replace(" ", "");
            Files.write(Paths.get(folder, path), sound.getBytes());  //save in backend
            String newPath = folder + "/" + path;
            podcast.setLink(newPath); // to read file
            podcast.setUser(user);
            podcastRepository.save(podcast);
        }
        else {
            throw new ApiRequestException("only mp3,wav,mpeg");
        }
    }

    public List<Podcast> getPodcasts() {
        return podcastRepository.findAll();
    }

    public boolean deletePodcast(Integer id) throws IOException {
        Podcast podcast = podcastRepository.findById(id).orElse(null);
        if(podcast == null) {
            throw new ApiRequestException("This podcast doesn't exist");
        }
        podcastRepository.deleteById(id);
        return true;
    }


    public Podcast getPodcastByUserId(Integer id) throws IOException{
        User user = userRepository.findById(id).orElse(null);
        if(user == null) {
            throw new ApiRequestException("This user doesn't exist");
        }
        return podcastRepository.findPodcastsByUserId(id);
    }


    public String getPodcastById(Integer id) throws IOException {
        Podcast podcast = podcastRepository.findById(id).orElse(null);
        if(podcast == null) {
            throw new ApiRequestException("This podcast doesn't exist");
        }
        Path path = Paths.get(podcast.getLink());
        byte[] soundByte = Files.readAllBytes(path);
        podcast.setLink("data:audio/ogg;base64," + Base64.getEncoder().encodeToString(soundByte));
        return podcast.getLink();
    }


//        public void updatePodcast(Integer id, Podcast podcast) throws IOException {
//        Optional<Podcast> oldPodcast = podcastRepository.findById(id);
//        if(oldPodcast.isEmpty()) {
//            throw new ApiRequestException("Invalid id");
//        }
//        oldPodcast.get().setId(podcast.getId());
//        oldPodcast.get().setName(podcast.getName());
//        oldPodcast.get().setDescription(podcast.getDescription());
//        oldPodcast.get().setType(podcast.getType());
//        podcastRepository.save(oldPodcast.get());
//    }

}

