package com.example.podcasts.Controller;


import com.example.podcasts.Model.Podcast;
import com.example.podcasts.Service.PodcastService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/podcast")
public class PodcastController {

    private final PodcastService podcastService;

    @PostMapping("/{id}")
    public ResponseEntity addPodcastByUser(@PathVariable Integer id ,@Valid @RequestPart Podcast podcast, @RequestPart MultipartFile sound) throws IOException {
        podcastService.addPodcastByUser(id,podcast,sound);
//        System.out.println(id + "----" + podcast  + "-----" + sound);
        return ResponseEntity.status(201).body("Podcast Added");
    }

    // Get all podcasts
    @GetMapping
    public ResponseEntity<List<Podcast>> getPodcasts(){
        return ResponseEntity.status(200).body(podcastService.getPodcasts());
    }

//    @PutMapping ("/{id}")
//    public ResponseEntity updatePodcast(@PathVariable Integer id, @Valid @RequestBody Podcast podcast) throws IOException {
//        podcastService.updatePodcast(id,podcast);
//        return ResponseEntity.status(200).body("Podcast Updated");
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePodcast(@PathVariable Integer id) throws IOException {
        podcastService.deletePodcast(id);
        return ResponseEntity.status(200).body("Podcast Deleted");
    }


    @GetMapping("/{id}")
    public ResponseEntity<Podcast> getPodcastByUserId(@PathVariable Integer id) throws IOException {
        return ResponseEntity.status(200).body(podcastService.getPodcastByUserId(id));
    }

    @GetMapping("/getPodcastById/{id}")
    public ResponseEntity getPodcastById(@PathVariable Integer id) throws IOException {
        return ResponseEntity.status(200).body(podcastService.getPodcastById(id));
    }

}
