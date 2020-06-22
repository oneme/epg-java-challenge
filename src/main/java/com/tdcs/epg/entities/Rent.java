package com.tdcs.epg.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "rents")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "vg_id")
    private VideoGame videoGame;

    private String rented;
    private String promisedReturn;
    private String returned;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public VideoGame getVideoGame() {
        return videoGame;
    }

    public void setVideoGame(VideoGame videoGame) {
        this.videoGame = videoGame;
    }

    public String getRented() {
        return rented;
    }

    public void setRented(String rented) {
        this.rented = rented;
    }

    public String getPromisedReturn() {
        return promisedReturn;
    }

    public void setPromisedReturn(String promisedReturn) {
        this.promisedReturn = promisedReturn;
    }

    public String getReturned() {
        return returned;
    }

    public void setReturned(String returned) {
        this.returned = returned;
    }

    @Override
    public String toString() {
        return "Rent{" +
                "id=" + id +
                ", videoGame=" + videoGame +
                ", rented='" + rented + '\'' +
                ", promisedReturn='" + promisedReturn + '\'' +
                ", returned='" + returned + '\'' +
                '}';
    }
}
