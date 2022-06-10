package com.example.podcasts.Repository;

import com.example.podcasts.Model.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayListRepository extends JpaRepository<PlayList,Integer> {
    PlayList findPlayListByUserId(Integer id);
    List<PlayList> findAllByUserId(Integer id);
}
