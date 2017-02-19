package com.example.joyrasmussen.hw5_group34;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * HomeWork 5
 * Group 34
 * Joy Rasmussen and Robert Holt
 * GameDetail.java
 */

public class GameDetail extends Game implements Serializable{
        String imageUrl, videoUrl, overview, publisher;
        ArrayList<String> genre;
        ArrayList<String> similar;
    public GameDetail(){
        super();

    }
    public GameDetail(Game game){
            super(game);


    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }

    public ArrayList<String> getSimilar() {
        return similar;
    }

    public void setSimilar(ArrayList<String> similar) {
        this.similar = similar;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
