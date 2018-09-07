package com.example.aisummarizer.aisummarizer.aisummarizer;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.aisummarizer.aisummarizer.models.LangModel;
import com.example.aisummarizer.aisummarizer.R;
import com.example.aisummarizer.aisummarizer.adapters.SpinnerAdapter;
import com.example.aisummarizer.aisummarizer.service_calls.AISummarizer;
import com.example.aisummarizer.aisummarizer.service_calls.request_builder.SummarizerRequest;
import com.example.aisummarizer.aisummarizer.service_calls.response_builder.SummarizerModel;
import com.example.aisummarizer.aisummarizer.super_class.SuperCompatActivity;
import com.example.aisummarizer.aisummarizer.utils.ApplicationHolder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class AISummarizerActivity extends SuperCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    TextView contextLabel, clearTv, summarizeTv, summarizeValueTv;
    ImageView fullScreenTv;
    LinearLayout textTv, fileTv, webTv;
    EditText contextEt;
    List<LangModel> langModels;
    Spinner spinnerLang;
    LangModel selectedLang = new LangModel();

    //Service interface
    AISummarizer aiSummarizer;

    // Retrofit instance
    Retrofit retrofit;

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

        fullScreenTv = (ImageView) findViewById(R.id.fullScreenTv);
        clearTv = (TextView) findViewById(R.id.clearTv);
        summarizeTv = (TextView) findViewById(R.id.summarizeTv);
        summarizeValueTv = (TextView) findViewById(R.id.summarizeValueTv);


        textTv.setOnClickListener(this);
        fileTv.setOnClickListener(this);
        webTv.setOnClickListener(this);

        fullScreenTv.setOnClickListener(this);
        clearTv.setOnClickListener(this);
        summarizeTv.setOnClickListener(this);

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

        if (contextEt != null) {
            contextEt.setMaxHeight(300);
            contextEt.setMinHeight(300);
        }

        retrofit = new Retrofit.Builder()
                .baseUrl(ApplicationHolder.baseUrl2)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        aiSummarizer = retrofit.create(AISummarizer.class);
    }

    int selectedPosition = 0;

    @Override
    public void onClick(View v) {

        // So we will make
        switch (v.getId() /*to get clicked view id**/) {
            case R.id.textTv:

                selectedPosition = 0;
                contextLabel.setText(getResources().getString(R.string.lb_your_text));
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

                selectedPosition = 1;
                contextEt.setText("");
                contextLabel.setText(getResources().getString(R.string.lb_your_file));
                contextEt.setMaxHeight(300);
                contextEt.setMinHeight(300);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    fileTv.setBackground(getResources().getDrawable(R.drawable.bg_text_border));
                    textTv.setBackground(null);
                    webTv.setBackground(null);
                }
                break;
            case R.id.webTv:

                selectedPosition = 2;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        contextEt.setText("");
                        contextLabel.setText(getResources().getString(R.string.lb_your_url));
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
//            case text = "http://dev.aisummarizer.com/summarizer/online/TextSummarizer"
//            case file = "http://dev.aisummarizer.com/summarizer/online/FileSummarizer"
//            case web = "http://dev.aisummarizer.com/summarizer/online/WebSummarizer"
                SummarizerRequest request = new SummarizerRequest("en", "json.english", "none", contextEt.getText().toString());


                if (selectedPosition == 2)
                    summarizeWeb(request);
                else
                    summarizeText(request);

                break;
            default:
                break;
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedLang = langModels.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /*Service calls*/
    private void summarizeText(SummarizerRequest request) {

        try {

            Call<List<SummarizerModel>> summarizerResponse = aiSummarizer.summarizerText(request.getLanguage(),
                    request.getStyle(),
                    request.getDomains(),
                    request.getContent());

            summarizerResponse.enqueue(new Callback<List<SummarizerModel>>() {
                @Override
                public void onResponse(Call<List<SummarizerModel>> call, Response<List<SummarizerModel>> response) {

                    List<SummarizerModel> summarizerModelsResponse = response.body();
                    if (summarizerModelsResponse != null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                StringBuilder builder = new StringBuilder();
                                String prefix = " ";
                                for (SummarizerModel model : summarizerModelsResponse) {
                                    if (model.getLevel() != null)
                                        if (Float.parseFloat(model.getLevel()) >= 70.0f) {
                                            builder.append(model.getText());
                                            builder.append(prefix);
                                        }
                                }
                                summarizeValueTv.setText(builder.toString());
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<List<SummarizerModel>> call, Throwable t) {

                }
            });

        } catch (Exception e) {

        }
    }


    private void summarizeWeb(SummarizerRequest request) {

        try {

            Call<List<SummarizerModel>> summarizerResponse = aiSummarizer.summarizerWeb(request.getLanguage(),
                    request.getStyle(),
                    request.getDomains(),
                    request.getContent());

            summarizerResponse.enqueue(new Callback<List<SummarizerModel>>() {
                @Override
                public void onResponse(Call<List<SummarizerModel>> call, Response<List<SummarizerModel>> response) {

                    List<SummarizerModel> summarizerModelsResponse = response.body();
                    if (summarizerModelsResponse != null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                StringBuilder builder = new StringBuilder();
                                String prefix = " ";
                                for (SummarizerModel model : summarizerModelsResponse) {
                                    if (Float.parseFloat(model.getLevel()) >= 90.0) {
                                        builder.append(model.getText());
                                        builder.append(prefix);
                                    }
                                }
                                summarizeValueTv.setText(builder.toString());
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<List<SummarizerModel>> call, Throwable t) {

                }
            });

        } catch (Exception e) {

        }
    }


}
