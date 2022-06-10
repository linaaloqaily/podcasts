package com.example.podcasts.Repository;

import com.example.podcasts.Model.Podcast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PodcastRepository extends JpaRepository<Podcast,Integer> {
    Podcast findPodcastsByUserId(Integer id);
}
