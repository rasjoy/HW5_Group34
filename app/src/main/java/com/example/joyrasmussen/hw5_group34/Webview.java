package com.example.joyrasmussen.hw5_group34;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.List;

public class Webview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        String url = null ; //getIntent().getStringExtra("URL");

        Intent intent = getIntent();
        String action = intent.getAction();

        if(Intent.ACTION_VIEW.equals(action)){
            final List<String> segments = intent.getData().getPathSegments();
            if(segments.size() >=1){
                url = segments.get(0);
            }

        }

        WebView webview =(WebView) findViewById(R.id.webview2);
        webview.setVisibility(View.VISIBLE);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient());
        webview .getSettings().setDomStorageEnabled(true);
        DisplayMetrics metric = getResources().getDisplayMetrics();
        int w = (int) ( metric.widthPixels / metric.density);
        int h = w * 3/5;

        String toLoad = "<html><body><iframe class=\"youtube-player\" type=\"text/html5\" width=\""
                    +( w - 20) + "\" height=\"" + h + "\" src=\"" + url +
                    "\" frameborder=\"0\"\"allowfullscreen\"></iframe></body></html>";

        //webview.loadData(toLoad, "text/html5", "utf-8");
         webview.loadUrl(url);


    }
}
