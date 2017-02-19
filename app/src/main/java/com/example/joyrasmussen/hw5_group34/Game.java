package com.example.joyrasmussen.hw5_group34;

import java.io.Serializable;

/**
 * HW 05
 * Robert Holt & Joy Rasmussen
 * Group 34
 * Game.java
*/

public class Game implements Serializable{
    String id, title, platform, release;

    public Game(Game game){
        id = game.getId();
        title = game.getTitle();
        platform = game.getPlatform();
        release = game.getRelease();

    }

    public Game(){
        id = title = platform = release = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }
}
