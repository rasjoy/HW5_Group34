package com.example.joyrasmussen.hw5_group34;

import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * HomeWork 5
 * Group 34
 * Joy Rasmussen and Robert Holt
 * SimilarGamesAsync.java
 */

public class SimilarGamesAsync extends AsyncTask<String, Void, Game> {
    String BASE_URL = "http://thegamesdb.net/api/GetGame.php?id=";
    SimilarGames similarGames;

    public SimilarGamesAsync(SimilarGames similarGames){
        this.similarGames = similarGames;

    }

    @Override
    protected void onCancelled(Game game) {
        super.onCancelled(game);

    }

    @Override
    protected void onPostExecute(Game games) {
        super.onPostExecute(games);
        similarGames.setArrayList(games);

    }

    @Override
    protected Game doInBackground(String... params) {

        URL url = null;
            try {
                url = new URL(BASE_URL + params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                int status = connection.getResponseCode();
                if (status == HttpURLConnection.HTTP_OK) {
                    InputStream in = connection.getInputStream();
                    return GameDetailUtil.GameDetailParser.parseDetail(in);
                }

                url = null;
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

    }

