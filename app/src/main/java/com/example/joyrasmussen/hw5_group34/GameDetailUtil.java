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

                                baseURL = parser.nextText().trim();
                                break;
                            case "Overview":
                                gameDetail.setOverview(parser.nextText().trim());
                                break;
                            case "genre":
                                genre.add(parser.nextText().trim());
                                break;
                            case "Youtube":
                                gameDetail.setVideoUrl(parser.nextText().trim());
                                break;
                            case "Publisher":
                                gameDetail.setPublisher(parser.nextText().trim());
                                break;
                            case "id":
                                String text = parser.nextText().trim();
                                if(!text.equals(gameDetail.getId())){
                                    similar.add(text);

                                }
                                break;
                            case "thumb":
                                if(gameDetail.getImageUrl() == null){
                                   gameDetail.setImageUrl(baseURL + parser.nextText().trim());
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
                                case "Similar":
                                    gameDetail.setSimilar(similar);
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
        public static Game parseDetail(InputStream in) throws XmlPullParserException, IOException {
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();

            parser.setInput(in, "UTF_8");
            Game game = null;

            int event = parser.getEventType();
            while(event != XmlPullParser.END_DOCUMENT){
                if(event == XmlPullParser.START_TAG){
                    switch (parser.getName()) {
                        case "id":
                            if(game.getId() == null) {
                                game.setId(parser.nextText().trim());
                            }
                            break;
                        case "Platform":
                            game.setPlatform(parser.nextText().trim());
                            break;
                        case "GameTitle":
                            game.setTitle(parser.nextText().trim());
                            break;


                    }
                }else if( event == XmlPullParser.END_TAG){
                    if(parser.getName().equals("GameTitle")){

                        return game;
                    }
                }

                event = parser.next();
            }
            return game;
            }
        }


    }


