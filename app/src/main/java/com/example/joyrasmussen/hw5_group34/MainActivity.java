
package com.example.joyrasmussen.hw5_group34;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

/*
HW 05
MainActivity.java
Robert Holt & Joy Rasmussen
Group 34
 */


public class MainActivity extends AppCompatActivity {

    public static final String GAME = "GAME";

    EditText searchEditText;
    ProgressBar progBar;
    ScrollView scrollView;
    Button goButton;
    TextView noResultsTextView;

    ArrayList<Game> gamesList;
    LinearLayout linearLayout;
    RadioGroup rg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchEditText = (EditText) findViewById(R.id.editText);
        progBar = (ProgressBar) findViewById(R.id.progressBar);
        scrollView = (ScrollView) findViewById(R.id.scrollView2);
        linearLayout = (LinearLayout) findViewById(R.id.LinearLayout);
        goButton = (Button) findViewById(R.id.goButton);
        gamesList = new ArrayList<Game>();
        noResultsTextView = (TextView) findViewById(R.id.noResultsTextView);

        rg = new RadioGroup(this);
        rg.setOrientation(RadioGroup.VERTICAL);
    }

    public void showResults(){

        progBar.setVisibility(View.GONE);

        if(gamesList == null || gamesList.isEmpty()){

            noResultsTextView.setVisibility(View.VISIBLE);

        } else {
            noResultsTextView.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
            createRadioButton(gamesList.size());
        }
    }

    public void search(View view){

        goButton.setEnabled(false);
        if(gamesList != null) {
            gamesList.clear();
        }
        rg.clearCheck();
        rg.removeAllViews();
        linearLayout.removeAllViews(); //These 5 statements are in case we are searching again

        progBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);
        noResultsTextView.setVisibility(View.GONE);

        String searchTerm = searchEditText.getText().toString().replace(" ", "");
        String URL = "http://thegamesdb.net/api/GetGamesList.php?name=" + searchTerm;

        GamesListAsync downloadTask = new GamesListAsync(this);
        downloadTask.execute(URL);

    }

    private void createRadioButton(int length) {

        final RadioButton[] rb = new RadioButton[length];

        for (int i = 0; i < length; i++) {

            Game g = gamesList.get(i);

            rb[i] = new RadioButton(this);

            rb[i].setText(g.getTitle() + ", " + g.getPlatform() + ", " + g.getRelease());

            rb[i].setId(i);
            rg.addView(rb[i]);
        }

        linearLayout.addView(rg);
        goButton.setEnabled(true);

    }


    public void goClick(View v){

        int id = rg.getCheckedRadioButtonId();

        if(id != -1) {

            Game game = gamesList.get(id);

            Intent intent = new Intent("com.example.joyrasmussen.hw5_group34.intent.action.Details");
            intent.putExtra("game", game);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Select a game", Toast.LENGTH_SHORT).show();
        }
    }

    public void setGamesList(ArrayList<Game> gamesList) {
        this.gamesList = gamesList;
    }
}
