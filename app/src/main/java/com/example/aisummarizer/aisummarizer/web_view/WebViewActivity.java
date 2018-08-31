package com.example.aisummarizer.aisummarizer.web_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.aisummarizer.aisummarizer.R;
import com.example.aisummarizer.aisummarizer.super_class.SuperCompatActivity;

@SuppressLint("SetJavaScriptEnabled")
public class WebViewActivity extends SuperCompatActivity {

    WebView webView;
    ProgressBar progressBar;
    private String mUrl;


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        Bundle bundle = savedInstanceState;
        if (bundle == null) {
            bundle = getIntent().getExtras();
        }
        mUrl = bundle.getString("url");

        webView.loadUrl(mUrl);
//        webView.set
        WebSettings settings = webView.getSettings();
        //settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                onLoading();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                onComplete();
            }
        });
    }

    public void onLoading() {
        //overlay.setVisibility(View.VISIBLE);
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    public void onComplete() {
        //overlay.setVisibility(View.GONE);
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        webView = (WebView) findViewById(R.id.general_web_view);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

    }

    public static Intent newIntent(Context context, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("url", url);

        return intent;
    }
}
