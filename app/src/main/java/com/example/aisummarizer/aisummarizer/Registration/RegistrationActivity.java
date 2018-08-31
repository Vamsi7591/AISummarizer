package com.example.aisummarizer.aisummarizer.Registration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.aisummarizer.aisummarizer.R;
import com.example.aisummarizer.aisummarizer.super_class.SuperCompatActivity;

public class RegistrationActivity extends SuperCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public void alreadyRegistered(View view) {
        finish();
    }
}
