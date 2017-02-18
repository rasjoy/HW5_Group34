package com.example.joyrasmussen.hw5_group34;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by joyrasmussen on 2/18/17.
 */

public class GameDetailUtil {

    static class GameDetailParser{


        public static GameDetail parseDetail(InputStream in, Game game) throws XmlPullParserException, IOException {

           String baseURL = null;
            ArrayList<String> genre = new ArrayList<>();
            ArrayList<String> similar = new ArrayList<>();
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();

            parser.setInput(in, "UTF_8");
            GameDetail gameDetail  = new GameDetail(game);
            int event = parser.getEventType();
            while(event != XmlPullParser.END_DOCUMENT){
                switch (event){
                    case XmlPullParser.START_TAG:
                        switch (parser.getName()){
                            case "baseImgUrl":
                                baseURL = parser.getText().trim();
                                break;
                            case "Overview":
                                gameDetail.setOverview(parser.getText().trim());
                                break;
                            case "genre":
                                genre.add(parser.getText().trim());
                                break;
                            case "Youtube":
                                gameDetail.setVideoUrl(parser.getText().trim());
                                break;
                            case "Publisher":
                                gameDetail.setPublisher(parser.getText().trim());
                                break;
                            case "Similar":
                                parser.next();
                               while(!parser.getName().equals("Similar")){
                                   if(parser.getName().equals("id")){
                                        similar.add(parser.getText().trim());
                                   }
                                   parser.next();
                               }
                                gameDetail.setSimilar(similar);
                                break;
                            case "thumb":
                                if(gameDetail.getImageUrl() == null){
                                   gameDetail.setImageUrl(baseURL + parser.getName().trim());
                                }
                                break;
                            case "boxart":
                                if(gameDetail.getImageUrl() == null){
                                    if(parser.getAttributeValue(null, "side").equals("front")){
                                        gameDetail.setImageUrl(baseURL+ parser.getAttributeValue(null,"thumb"));
                                    }
                                }
                                break;

                        }
                        break;
                    case XmlPullParser.END_TAG:
                            switch (parser.getName()){
                                case "Genres":
                                    gameDetail.setGenre( genre);
                                    break;
                                default:
                                    break;

                            }

                        break;




                }

                event = parser.next();
            }
            return gameDetail;
        }



    }

}
