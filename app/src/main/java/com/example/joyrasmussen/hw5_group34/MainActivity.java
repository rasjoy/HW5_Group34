package com.example.joyrasmussen.hw5_group34;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String GAME = "GAME";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void goClick(View v){
        Intent intent = new Intent("com.example.joyrasmussen.hw5_group34.intent.action.Details");

        startActivity(intent);
    }
}
