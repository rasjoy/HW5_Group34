package com.example.joyrasmussen.hw5_group34;

import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * HomeWork 5
 * Group 34
 * Joy Rasmussen and Robert Holt
 * GameDetailAsync.java
 */

public class GameDetailsAsync extends AsyncTask<Game, Void, GameDetail>{
    GameDetailsActivity gameDetailsActivity;
    String BASE_URL = "http://thegamesdb.net/api/GetGame.php?id=";

    public GameDetailsAsync(GameDetailsActivity gameDetailsActivity) {
        this.gameDetailsActivity = gameDetailsActivity;
    }

    @Override
    protected GameDetail doInBackground(Game... params) {
        URL url = null;


        try {
            url = new URL(BASE_URL + params[0].getId());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int status = connection.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                InputStream in = connection.getInputStream();
                return GameDetailUtil.GameDetailParser.parseDetail(in, params[0]);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
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
