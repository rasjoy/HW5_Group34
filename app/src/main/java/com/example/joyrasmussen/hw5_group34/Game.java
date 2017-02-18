package com.example.joyrasmussen.hw5_group34;

/**
 * Created by joyrasmussen on 2/15/17.
 */

public class Game {
    String id, title, platform, release;
    public Game(){

    }
    public Game(Game game){
        id = game.getId();
        title = game.getTitle();
        platform = game.getPlatform();
        release = game.getRelease();


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
