package com.tdcs.epg.servicesImp;


import com.tdcs.epg.entities.VideoGame;
import com.tdcs.epg.repositories.VideoGameRepository;
import com.tdcs.epg.services.VideoGameService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoGameServiceImp implements VideoGameService {

    private final VideoGameRepository videoGameRepository;

    public VideoGameServiceImp(VideoGameRepository videoGameRepository) {
        this.videoGameRepository = videoGameRepository;
    }

    @Override
    public List<VideoGame> getAvailableVideoGames() {
        return videoGameRepository.findVideoGamesByStatusIsFalse();
    }

    @Override
    public List<VideoGame> showVideoGames() {
        return videoGameRepository.findAll();
    }

}
