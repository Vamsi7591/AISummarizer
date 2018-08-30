package com.example.aisummarizer.aisummarizer.aisummarizer;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.aisummarizer.aisummarizer.LangModel;
import com.example.aisummarizer.aisummarizer.R;
import com.example.aisummarizer.aisummarizer.adapters.SpinnerAdapter;
import com.example.aisummarizer.aisummarizer.super_class.SuperCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AISummarizerActivity extends SuperCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    TextView contextLabel, fullScreenTv, clearTv, summarizeTv;
    LinearLayout textTv, fileTv, webTv;
    EditText contextEt;
    List<LangModel> langModels;
    Spinner spinnerLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aisummarizer);

        textTv = (LinearLayout) findViewById(R.id.textTv);
        fileTv = (LinearLayout) findViewById(R.id.fileTv);
        webTv = (LinearLayout) findViewById(R.id.webTv);

        contextLabel = (TextView) findViewById(R.id.contextLabel);
        contextEt = (EditText) findViewById(R.id.contextEt);
        spinnerLang = (Spinner) findViewById(R.id.spinnerLang);

        fullScreenTv = (TextView) findViewById(R.id.fullScreenTv);
        clearTv = (TextView) findViewById(R.id.clearTv);
        summarizeTv = (TextView) findViewById(R.id.summarizeTv);


        textTv.setOnClickListener(this);
        fileTv.setOnClickListener(this);
        webTv.setOnClickListener(this);
        fullScreenTv.setOnClickListener(this);
        clearTv.setOnClickListener(this);
        summarizeTv.setOnClickListener(this);

        contextEt.setMaxHeight(300);
        contextEt.setMinHeight(300);

        langModels = new ArrayList<>();
        langModels.add(new LangModel("Arabic", "ar"));
        langModels.add(new LangModel("Chinese", "zh"));
        langModels.add(new LangModel("Dutch", "nl"));
        langModels.add(new LangModel("English", "en"));
        langModels.add(new LangModel("French", "fr"));
        langModels.add(new LangModel("German", "de"));
        langModels.add(new LangModel("Greek", "el"));
        langModels.add(new LangModel("Hebrew", "iw"));
        langModels.add(new LangModel("Hindi", "hi"));
        langModels.add(new LangModel("Indonesian", "in"));
        langModels.add(new LangModel("Italian", "it"));
        langModels.add(new LangModel("Japanese", "ja"));
        langModels.add(new LangModel("Korean", "ko"));
        langModels.add(new LangModel("Norwegian", "no"));
        langModels.add(new LangModel("Persian", "fa"));
        langModels.add(new LangModel("Polish", "pl"));
        langModels.add(new LangModel("Portuguese", "pt"));
        langModels.add(new LangModel("Russian", "ru"));
        langModels.add(new LangModel("Spanish", "es"));
        langModels.add(new LangModel("Swedish", "sv"));
        langModels.add(new LangModel("Turkish", "tr"));


        //Custom spinner
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(AISummarizerActivity.this, langModels);
        spinnerLang.setAdapter(spinnerAdapter);

        spinnerLang.setOnItemSelectedListener(this);

        spinnerLang.setSelection(3);
        selectedLang = new LangModel("English", "en");
    }

    @Override
    public void onClick(View v) {

        // So we will make
        switch (v.getId() /*to get clicked view id**/) {
            case R.id.textTv:

                contextLabel.setText("Your Text");
                contextEt.setText("");
                contextEt.setMaxHeight(300);
                contextEt.setMinHeight(300);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    textTv.setBackground(getResources().getDrawable(R.drawable.bg_text_border));
                    webTv.setBackground(null);
                    fileTv.setBackground(null);
                }
                break;
            case R.id.fileTv:
                contextEt.setText("");
                contextLabel.setText("Your File");
                contextEt.setMaxHeight(300);
                contextEt.setMinHeight(300);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    fileTv.setBackground(getResources().getDrawable(R.drawable.bg_text_border));
                    textTv.setBackground(null);
                    webTv.setBackground(null);
                }
                break;
            case R.id.webTv:

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        contextEt.setText("");
                        contextLabel.setText("Your URL");
                        contextEt.setMaxHeight(100);
                        contextEt.setMinHeight(100);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            webTv.setBackground(getResources().getDrawable(R.drawable.bg_text_border));
                            textTv.setBackground(null);
                            fileTv.setBackground(null);
                        }
                    }
                });

                break;

            case R.id.fullScreenTv:

                break;

            case R.id.clearTv:
                contextEt.setText("");
                break;

            case R.id.summarizeTv:

                break;
            default:
                break;
        }
    }

    LangModel selectedLang = new LangModel();

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedLang = langModels.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
