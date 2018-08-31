package com.example.aisummarizer.aisummarizer.faq;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.example.aisummarizer.aisummarizer.R;
import com.example.aisummarizer.aisummarizer.super_class.SuperCompatActivity;

public class FaqActivity extends SuperCompatActivity {

    TextView faq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        faq = (TextView) findViewById(R.id.faq);
        String s = getResources().getString(R.string.faq);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            faq.setText(Html.fromHtml(s, Html.FROM_HTML_MODE_COMPACT));
        }

    }
}
