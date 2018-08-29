package com.example.aisummarizer.aisummarizer.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.aisummarizer.aisummarizer.R;
import com.example.aisummarizer.aisummarizer.home.MainActivity;
import com.example.aisummarizer.aisummarizer.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Intent main_activity = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(main_activity);
    }
}
