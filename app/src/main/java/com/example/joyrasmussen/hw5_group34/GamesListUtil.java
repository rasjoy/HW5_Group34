package com.example.joyrasmussen.hw5_group34;
/**
 * HomeWork 5
 * Group 34
 * Joy Rasmussen and Robert Holt
 * GamelistUtil.java
 */

import android.util.Log;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/*
HW 05
MainActivity.java
Robert Holt & Joy Rasmussen
Group 34
 */

public class GamesListUtil {

    public static class GamesListPullParser {

        static public ArrayList<Game> parseList(InputStream in) throws XmlPullParserException, IOException {

            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(in, "UTF-8");

            Game game = null;
            ArrayList<Game> gamesList = new ArrayList<Game>();

            int event = parser.getEventType();

            while(event != XmlPullParser.END_DOCUMENT){

                switch (event) {

                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("Game")) {
                            game = new Game();
                        } else if (parser.getName().equals("id")) {
                            game.setId(parser.nextText().trim());
                        } else if (parser.getName().equals("GameTitle")) {
                            game.setTitle(parser.nextText().trim());
                        } else if (parser.getName().equals("ReleaseDate")) {
                            game.setRelease(parser.nextText().trim());
                        } else if (parser.getName().equals("Platform")) {
                            game.setPlatform(parser.nextText().trim());
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("Game")){
                            if(game.getRelease().equals("")){
                                game.setRelease("Unknown release");
                            }
                            gamesList.add(game);
                            game = null;
                        }
                        break;
                }
                event = parser.next();
            }

            return gamesList;
        }
    }
}
