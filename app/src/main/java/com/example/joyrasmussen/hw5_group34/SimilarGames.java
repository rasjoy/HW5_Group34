package com.example.joyrasmussen.hw5_group34;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
/**
 * HomeWork 5
 * Group 34
 * Joy Rasmussen and Robert Holt
 * SimilarGames.java
 */
public class SimilarGames extends AppCompatActivity {
    LinearLayout layout;
    TextView title;
    TextView loading;
    ArrayList<Game> gameLists;
    GameDetail dets;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similar_games);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        loading = (TextView) findViewById(R.id.loadingView);
        layout = (LinearLayout) findViewById(R.id.textHere);
        title = (TextView) findViewById(R.id.similarGames2);

        gameLists = new ArrayList<>();
        dets = (GameDetail) getIntent().getSerializableExtra("GAME_DET");

        title.append(" " + dets.getTitle());


        for(String id: dets.getSimilar()){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                new SimilarGamesAsync(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, id);
            }else {
                new SimilarGamesAsync(this).execute(id);
            }

        }

    }
    public void setSimilar(){
        progressBar.setVisibility(View.INVISIBLE);
        loading.setVisibility(View.INVISIBLE);

        for(Game game: gameLists){
            TextView text = new TextView(this);
            text.setText("");

            if(game.getTitle() != null){
                text.append(game.getTitle() + ".");
            }else{
                text.append("n/a. ");

            }
            if(game.getRelease() != null){
                text.append(" Released in " + game.getRelease() + ". ");
            }else{
                text.append("Released in n/a.");
            }
            if(game.getPlatform() != null){
                text.append("Platform: " + game.getPlatform());
            }else{
                text.append("Platform: n/a");
            }

            layout.addView(text);
        }



    }
    public void setArrayList(Game games){
        gameLists.add(games);
        if(gameLists.size() == dets.getSimilar().size()){
            setSimilar();
        }


    }

    public void finishSimilar(View v){
        finish();


    }
}
