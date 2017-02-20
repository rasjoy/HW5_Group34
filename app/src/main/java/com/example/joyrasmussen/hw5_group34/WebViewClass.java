package com.example.joyrasmussen.hw5_group34;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebViewClass extends AppCompatActivity {
    String gameName;
    String youTube;
    String videoID;
    WebView webview;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        gameName = getIntent().getStringExtra("GAME");
        youTube = getIntent().getStringExtra("URL");

        String pattern = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";

        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(youTube);
        textView = (TextView) findViewById(R.id.copyright);
        textView.setText(gameName + " " + textView.getText());
        webview = (WebView) findViewById(R.id.framezzz);

        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setPluginState(WebSettings.PluginState.ON);

        webview.setWebChromeClient(new WebChromeClient());
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        DisplayMetrics metric = getResources().getDisplayMetrics();
        int w = (int) ( metric.widthPixels / metric.density);
        int h = w * 3/5;



        if(matcher.find()){
            videoID =  matcher.group();
        }

        String iframe = "<iframe class=\"youtube-player\" "
                + "style=\"border: 0; width:"+w +" height:" + h
                + "padding:0px; margin:0px\" "
                + "id=\"ytplayer\" type=\"text/html\" "
                + "src=\"http://www.youtube.com/embed/" + videoID
                + "?fs=0\" frameborder=\"0\" " + "allowfullscreen autobuffer "
                + "controls onclick=\"this.play()\">\n" + "</iframe>\n";

         webview.loadDataWithBaseURL("", iframe, "text/html","UTF-8", "");

    }
}
