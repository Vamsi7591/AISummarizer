package com.example.aisummarizer.aisummarizer.aisummarizer;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.aisummarizer.aisummarizer.R;
import com.example.aisummarizer.aisummarizer.adapters.SpinnerAdapter;
import com.example.aisummarizer.aisummarizer.full_screen.FullScreenActivity;
import com.example.aisummarizer.aisummarizer.models.LangModel;
import com.example.aisummarizer.aisummarizer.service_calls.AISummarizer;
import com.example.aisummarizer.aisummarizer.service_calls.request_builder.SummarizerRequest;
import com.example.aisummarizer.aisummarizer.service_calls.response_builder.SummarizerModel;
import com.example.aisummarizer.aisummarizer.super_class.SuperCompatActivity;
import com.example.aisummarizer.aisummarizer.utils.ApplicationHolder;
import com.example.aisummarizer.aisummarizer.utils.FileHelper;
import com.google.gson.Gson;
import com.obsez.android.lib.filechooser.ChooserDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AISummarizerActivity extends SuperCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    TextView contextLabel, clearTv, summarizeTv, summarizeValueTv;
    ImageView fullScreenTv;
    LinearLayout textTv, fileTv, webTv;
    ProgressBar mProgressBar;
    public ProgressDialog pDialog;
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

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);


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

        try {
            if (contextEt != null) {
                /*ViewGroup.LayoutParams params = contextEt.getLayoutParams();
                if (params != null) {
                    params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                } else
                    params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                contextEt.setLayoutParams(params);
                contextEt.setMaxHeight(300);
                contextEt.setMinHeight(300);*/

                String folder_main = "AISummarizer";

                File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), folder_main);
                if (!f.exists()) f.mkdirs();
            }
        } catch (Exception e) {
            Log.v("TAG", " --> " + e.getMessage());
        }

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(1000, TimeUnit.SECONDS)
                .readTimeout(1000, TimeUnit.SECONDS).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(ApplicationHolder.baseUrl2)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(client)
                .build();

        aiSummarizer = retrofit.create(AISummarizer.class);

        pDialog = new ProgressDialog(AISummarizerActivity.this);
        pDialog.setMessage("Summarizing...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
//        pDialog.show();
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
                contextEt.setHint("");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    textTv.setBackground(getResources().getDrawable(R.drawable.bg_text_border));
                    webTv.setBackground(null);
                    fileTv.setBackground(null);
                }
                break;
            case R.id.fileTv:

                closeKeyboard();
                selectedPosition = 1;
                contextEt.setText("");
                contextEt.setHint("");
                contextLabel.setText(getResources().getString(R.string.lb_your_file));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    fileTv.setBackground(getResources().getDrawable(R.drawable.bg_text_border));
                    textTv.setBackground(null);
                    webTv.setBackground(null);
                }

                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "AISummarizer";
                new ChooserDialog().with(this)
                        .withFilter(false, false, "txt")//"jpg", "jpeg", "png"
                        .withStartFile(path)
                        .withResources(R.string.choose_file, R.string.title_choose, R.string.dialog_cancel)
                        .withChosenListener(new ChooserDialog.Result() {
                            @Override
                            public void onChoosePath(String path, File pathFile) {
//                                Toast.makeText(AISummarizerActivity.this, "FILE: " + path, Toast.LENGTH_SHORT).show();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        contextEt.setText(FileHelper.ReadFile(AISummarizerActivity.this, path, path));
                                    }
                                });
                            }
                        })
                        .build()
                        .show();
                break;
            case R.id.webTv:

                selectedPosition = 2;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        contextEt.setText("");
                        contextEt.setHint(getResources().getString(R.string.hint_web_page));
                        contextLabel.setText(getResources().getString(R.string.lb_your_url));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            webTv.setBackground(getResources().getDrawable(R.drawable.bg_text_border));
                            textTv.setBackground(null);
                            fileTv.setBackground(null);
                        }
                    }
                });

                break;

            case R.id.fullScreenTv:

                //1. Convert object to JSON string
                Gson gson = new Gson();
                String json = gson.toJson(summarizerModelsResponse);
                //System.out.println(json);

                Intent intent1 = new Intent(AISummarizerActivity.this, FullScreenActivity.class);
                intent1.putExtra("S_Value", contextEt.getText().toString());
//                intent.putParcelableArrayListExtra("S_Object", json);
//                intent.putParcelableArrayListExtra("S_Object", (ArrayList<? extends Parcelable>) summarizerModelsResponse);
                startActivity(intent1);
                break;

            case R.id.clearTv:
                contextEt.setText("");
                summarizeValueTv.setText("");
                break;

            case R.id.summarizeTv:
//            case text = "http://dev.aisummarizer.com/summarizer/online/TextSummarizer"
//            case file = "http://dev.aisummarizer.com/summarizer/online/FileSummarizer"
//            case web = "http://dev.aisummarizer.com/summarizer/online/WebSummarizer"


                closeKeyboard();
//                mProgressBar.setVisibility(ProgressBar.VISIBLE);

                // dismiss the dialog once got all details
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                } else
                    pDialog.show();


                if (selectedPosition == 2) {
                    SummarizerRequest request = new SummarizerRequest("en", "json.english", "none", null, contextEt.getText().toString());
                    summarizeWeb(request);
                } else {
                    SummarizerRequest request = new SummarizerRequest("en", "json.english", "none", contextEt.getText().toString());
                    summarizeText(request);
                }

                break;
            default:
                break;
        }
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
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
    List<SummarizerModel> summarizerModelsResponse;

    private void summarizeText(SummarizerRequest request) {

        try {

            Call<List<SummarizerModel>> summarizerResponse = aiSummarizer.summarizerText(request.getLanguage(),
                    request.getStyle(),
                    request.getDomains(),
                    request.getContent());

            summarizerResponse.enqueue(new Callback<List<SummarizerModel>>() {
                @Override
                public void onResponse(Call<List<SummarizerModel>> call, Response<List<SummarizerModel>> response) {

                    summarizerModelsResponse = new ArrayList<>();
                    summarizerModelsResponse = response.body();
                    ApplicationHolder.summarizerModelList = summarizerModelsResponse;
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
//                    mProgressBar.setVisibility(ProgressBar.INVISIBLE);
                    // dismiss the dialog once got all details
                    if (pDialog.isShowing()) {
                        pDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<List<SummarizerModel>> call, Throwable t) {
//                    mProgressBar.setVisibility(ProgressBar.INVISIBLE);
                    // dismiss the dialog once got all details
                    if (pDialog.isShowing()) {
                        pDialog.dismiss();
                    }
                }
            });

        } catch (Exception e) {
//            mProgressBar.setVisibility(ProgressBar.INVISIBLE);
            // dismiss the dialog once got all details
            if (pDialog.isShowing()) {
                pDialog.dismiss();
            }
        }
    }


    private void summarizeWeb(SummarizerRequest request) {

        try {

            Call<List<SummarizerModel>> summarizerResponse = aiSummarizer.summarizerWeb(request.getLanguage(),
                    request.getStyle(),
                    request.getDomains(),
                    request.getUrl());

            summarizerResponse.enqueue(new Callback<List<SummarizerModel>>() {
                @Override
                public void onResponse(Call<List<SummarizerModel>> call, Response<List<SummarizerModel>> response) {

                    summarizerModelsResponse = new ArrayList<>();
                    summarizerModelsResponse = response.body();
                    ApplicationHolder.summarizerModelList = summarizerModelsResponse;
                    if (summarizerModelsResponse != null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                StringBuilder builder = new StringBuilder();
                                String prefix = " ";
                                for (SummarizerModel model : summarizerModelsResponse) {
                                    if (model.getLevel() != null)
                                        if (Float.parseFloat(model.getLevel()) >= 90.0) {
                                            builder.append(model.getText());
                                            builder.append(prefix);
                                        }
                                }
                                summarizeValueTv.setText(builder.toString());
                            }
                        });
                    }

//                    mProgressBar.setVisibility(ProgressBar.INVISIBLE);
                    // dismiss the dialog once got all details
                    if (pDialog.isShowing()) {
                        pDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<List<SummarizerModel>> call, Throwable t) {
//                    mProgressBar.setVisibility(ProgressBar.INVISIBLE);
                    // dismiss the dialog once got all details
                    if (pDialog.isShowing()) {
                        pDialog.dismiss();
                    }
                }
            });

        } catch (Exception e) {
//            mProgressBar.setVisibility(ProgressBar.INVISIBLE);
            // dismiss the dialog once got all details
            if (pDialog.isShowing()) {
                pDialog.dismiss();
            }
        }
    }


}
