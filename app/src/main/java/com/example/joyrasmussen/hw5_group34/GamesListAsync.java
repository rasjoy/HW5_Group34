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

public class GamesListAsync extends AsyncTask<String, Void, ArrayList<Game>> {

    MainActivity main;

    public GamesListAsync(MainActivity main) {
        this.main = main;
    }


    @Override
    protected ArrayList<Game> doInBackground(String... params) {

        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            InputStream in = con.getInputStream();

            return GamesListUtil.GamesListPullParser.parseList(in);

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
    protected void onPostExecute(ArrayList<Game> games) {
        super.onPostExecute(games);

        main.setGamesList(games);
        main.showResults();
    }
}
