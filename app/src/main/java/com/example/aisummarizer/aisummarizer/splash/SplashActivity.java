package com.example.aisummarizer.aisummarizer.splash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.aisummarizer.aisummarizer.R;
import com.example.aisummarizer.aisummarizer.home.MainActivity;
import com.example.aisummarizer.aisummarizer.login.LoginActivity;
import com.example.aisummarizer.aisummarizer.super_class.SuperCompatActivity;

public class SplashActivity extends SuperCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> enterToApp(), 1500);
    }

    private void enterToApp() {

        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        if (sharedPref.getBoolean("isActive", false)) {
            Intent main_activity = new Intent(getApplicationContext(), MainActivity.class);
            main_activity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(main_activity);
        } else {
            Intent main_activity = new Intent(getApplicationContext(), LoginActivity.class);
            main_activity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(main_activity);
        }

    }
}
