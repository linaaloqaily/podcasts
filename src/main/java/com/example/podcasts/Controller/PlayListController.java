package com.example.podcasts.Controller;


import com.example.podcasts.Model.PlayList;
import com.example.podcasts.Service.PlayListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/playList")
public class PlayListController {

    private final PlayListService playListService;

    @GetMapping
    public ResponseEntity<Object> getAllPlayList(){
        return ResponseEntity.status(200).body(playListService.getAllPlayList());
    }

    @GetMapping("/userId/{id}")
    public ResponseEntity<List<PlayList>> getPlayListByUserId(@PathVariable Integer id){
        return ResponseEntity.status(200).body(playListService.getPlayListByUserId(id));
    }

    @PostMapping("/{id}")
    public ResponseEntity addPlayList(@PathVariable Integer id,@Valid @RequestBody PlayList playList){
        playListService.addPlayListToUser(id,playList);
        return ResponseEntity.status(201).body("PlayList Added");
    }

    @PostMapping("/{podcastId}/{playListId}")
    public ResponseEntity addPodcastToPlayList(@PathVariable Integer podcastId , @PathVariable Integer playListId){
        playListService.addPodcastToPlayList(podcastId,playListId);
        return ResponseEntity.status(200).body("Added podcast to playList");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePlayList(@PathVariable Integer id){
        playListService.deletePlayList(id);
        return ResponseEntity.status(200).body("PlayList Deleted");
    }

}
