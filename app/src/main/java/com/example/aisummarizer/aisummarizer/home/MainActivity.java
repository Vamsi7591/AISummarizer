package com.example.aisummarizer.aisummarizer.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.aisummarizer.aisummarizer.R;
import com.example.aisummarizer.aisummarizer.aisummarizer.AISummarizerActivity;
import com.example.aisummarizer.aisummarizer.login.LoginActivity;
import com.example.aisummarizer.aisummarizer.service_calls.AISummarizer;
import com.example.aisummarizer.aisummarizer.super_class.SuperCompatActivity;

public class MainActivity extends SuperCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /*Click actions*/
    public void about(View view) {
    }

    public void faq(View view) {
    }

    public void summarize(View view) {
        Intent main_activity = new Intent(getApplicationContext(), AISummarizerActivity.class);
        startActivity(main_activity);
    }
    /*Click actions end*/
}
