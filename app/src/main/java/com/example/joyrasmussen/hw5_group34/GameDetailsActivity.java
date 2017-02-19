package com.example.joyrasmussen.hw5_group34;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.ArrayList;
/**
 * HomeWork 5
 * Group 34
 * Joy Rasmussen and Robert Holt
 * GameDetailsActivity.java
 */
public class GameDetailsActivity extends AppCompatActivity {
    GameDetail gameDetail;
    ImageView image;
    TextView title, overview, genre, publisher, loading;
    Button finish, similar, trailer;
    ProgressBar progressBar;
    ProgressBar pictureBar;
    WebView webview;
    Game game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);

        //load views and set invisible
        title = (TextView) findViewById(R.id.title);
        overview = (TextView) findViewById(R.id.overviewText);
        genre = (TextView) findViewById(R.id.genre);
        publisher = (TextView) findViewById(R.id.publisher);
        finish = (Button) findViewById(R.id.finishButton);
        similar = (Button) findViewById(R.id.similarButton);
        trailer = (Button) findViewById(R.id.trailerButton);
        image = (ImageView) findViewById(R.id.image);
        progressBar = (ProgressBar) findViewById(R.id.loadingProgress);
        loading = (TextView) findViewById(R.id.loadingText);
        pictureBar = (ProgressBar)findViewById(R.id.loadpic);

        game = (Game) getIntent().getSerializableExtra("game");
        new GameDetailsAsync(this).execute(game);

    }

    public void loaded(GameDetail games){
        if(games == null){
           Toast.makeText(this, "Retrying retreiving Game Detail", Toast.LENGTH_LONG).show();
            new GameDetailsAsync(this).execute(game);
            return;

        }
        gameDetail = games;

        loading.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        pictureBar.setVisibility(View.VISIBLE);
        finish.setEnabled(true);
        similar.setEnabled(true);
        trailer.setEnabled(true);
        title.setText(gameDetail.getTitle());
        title.setVisibility(View.VISIBLE);
        image.setVisibility(View.VISIBLE);
        if(gameDetail.getImageUrl() != null) {
            Picasso.with(this).load(gameDetail.getImageUrl()).into(image, new Callback() {
                @Override
                public void onSuccess() {
                    pictureBar.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onError() {

                }
            });
        }else{
            pictureBar.setVisibility(View.INVISIBLE);

        }
        overview.setText(gameDetail.getOverview());
        overview.setVisibility(View.VISIBLE);
        ArrayList<String> genreList = gameDetail.getGenre();
       if(genreList != null){
        if(1 == genreList.size()){
            genre.append(genreList.get(0));
        }else{

            for(String gen: genreList){
                genre.append(gen);
                if(genreList.indexOf(gen) != genreList.size()-1 ){
                    genre.append(", ");
                }
            }

        }
       }else{
           genre.append(" n/a");

       }
        genre.setVisibility(View.VISIBLE);
        publisher.setVisibility(View.VISIBLE);
       if(gameDetail.getPublisher() != null) {
           publisher.append(gameDetail.getPublisher());
       }else{
           publisher.append(" n/a");
       }
        }


    public void getSimilarGames(View v){
       if(gameDetail.getSimilar() != null){
           Intent intent = new Intent("com.example.joyrasmussen.hw5_group34.intent.action.Similar");
           intent.putExtra("GAME_DET", gameDetail);
           startActivity(intent);
       }else{
           Toast.makeText(this, "There are no similar games listed", Toast.LENGTH_LONG).show();

       }




    }
    public void playTrailer(View view){
        if(gameDetail.getVideoUrl() != null) {


            DisplayMetrics metric = getResources().getDisplayMetrics();
            int w = (int) ( metric.widthPixels / metric.density);
            int h = w * 3/5;

            webview =(WebView) findViewById(R.id.webview);
            webview.getSettings().setJavaScriptEnabled(true);
            webview.setWebViewClient(new WebViewClient(){
                @SuppressWarnings("deprecation")
                public boolean shouldOverrideUrlLoading(WebView view, String url) {

                    if (Uri.parse(url).getHost().equals("www.youtube.com")) {
                        // This is my web site, so do not override; let my WebView load the page
                        return false;
                    }
                    // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                    return true;
                }
            });
            webview.loadUrl(gameDetail.getVideoUrl());

        }else{
            Toast.makeText(this, "This video game does not have a trailer", Toast.LENGTH_LONG).show();

        }

    }
    public void finishDetail(View v){
        finish();

    }

}
