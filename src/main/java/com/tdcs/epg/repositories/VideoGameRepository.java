package com.tdcs.epg.repositories;


import com.tdcs.epg.entities.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoGameRepository extends JpaRepository<VideoGame, Long> {
    List<VideoGame> findVideoGamesByStatusIsFalse();
}
