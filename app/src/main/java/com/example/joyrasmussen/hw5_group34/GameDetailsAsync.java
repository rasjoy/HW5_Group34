package com.example.joyrasmussen.hw5_group34;

import android.os.AsyncTask;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by joyrasmussen on 2/15/17.
 */

public class GameDetailsAsync extends AsyncTask<Game, Void, GameDetail>{
    GameDetailsActivity gameDetailsActivity;
    String BASEURL = 

    public GameDetailsAsync(GameDetailsActivity gameDetailsActivity) {
        this.gameDetailsActivity = gameDetailsActivity;
    }

    @Override
    protected GameDetail doInBackground(Game... params) {
        URL url = null;


        try {
            url = new URL();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(GameDetail gameDetail) {
        super.onPostExecute(gameDetail);
        gameDetailsActivity.loaded(gameDetail);
    }
}
