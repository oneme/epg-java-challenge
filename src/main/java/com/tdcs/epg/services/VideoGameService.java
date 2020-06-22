package com.tdcs.epg.services;

import com.tdcs.epg.entities.VideoGame;

import java.util.List;

public interface VideoGameService {
    List<VideoGame> getAvailableVideoGames();
    List<VideoGame> showVideoGames();
}
