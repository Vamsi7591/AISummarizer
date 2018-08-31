package com.example.aisummarizer.aisummarizer.about;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.aisummarizer.aisummarizer.R;
import com.example.aisummarizer.aisummarizer.super_class.SuperCompatActivity;
import com.example.aisummarizer.aisummarizer.web_view.WebViewActivity;

public class AboutActivity extends SuperCompatActivity {


    String FaceBook = "https://apps.facebook.com/aisummarizer";

    String Twitter = "https://twitter.com/AIsummarizer";

    String Google = "https://plus.google.com/111670454931521627103";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void facebook(View view) {
        Intent intent = WebViewActivity.newIntent(this, FaceBook);
        startActivity(intent);

    }

    public void twitter(View view) {
        Intent intent = WebViewActivity.newIntent(this, Twitter);
        startActivity(intent);
    }

    public void gmail(View view) {
        Intent intent = WebViewActivity.newIntent(this, Google);
        startActivity(intent);
    }
}
