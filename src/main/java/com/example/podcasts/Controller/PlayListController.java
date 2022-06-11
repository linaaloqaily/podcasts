package com.example.podcasts.Controller;


import com.example.podcasts.Model.PlayList;
import com.example.podcasts.Service.PlayListService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/playList")
public class PlayListController {

    Logger logger = LoggerFactory.getLogger(PlayListController.class);
    private final PlayListService playListService;

    @GetMapping
    public ResponseEntity<Object> getAllPlayList(){
        logger.info("Get all playList");
        return ResponseEntity.status(200).body(playListService.getAllPlayList());
    }

    @GetMapping("/userId/{id}")
    public ResponseEntity<List<PlayList>> getPlayListByUserId(@PathVariable Integer id){
        logger.info("Get playList by user id");
        return ResponseEntity.status(200).body(playListService.getPlayListByUserId(id));
    }

    @PostMapping("/{userId}")
    public ResponseEntity addPlayList(@PathVariable Integer userId,@Valid @RequestBody PlayList playList) throws IOException {
        playListService.addPlayListToUser(userId,playList);
        logger.info("Add playList by user");
        return ResponseEntity.status(201).body("PlayList Added");
    }

    @PostMapping("/{podcastId}/{playListId}")
    public ResponseEntity addPodcastToPlayList(@PathVariable Integer podcastId , @PathVariable Integer playListId){
        playListService.addPodcastToPlayList(podcastId,playListId);
        logger.info("Add podcast to playList");
        return ResponseEntity.status(200).body("Added podcast to playList");
    }

    @DeleteMapping("/{playListId}")
    public ResponseEntity deletePlayList(@PathVariable Integer playListId){
        playListService.deletePlayList(playListId);
        logger.info("Delete playList");
        return ResponseEntity.status(200).body("PlayList Deleted");
    }

}
