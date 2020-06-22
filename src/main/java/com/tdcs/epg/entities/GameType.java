package com.tdcs.epg.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "game_type")
public class GameType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "type")
    @JsonBackReference
    private List<VideoGame> videoGame;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<VideoGame> getVideoGame() {
        return videoGame;
    }

    public void setVideoGame(List<VideoGame> videoGame) {
        this.videoGame = videoGame;
    }

    @Override
    public String toString() {
        return "GameType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
