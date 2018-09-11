package com.example.aisummarizer.aisummarizer.full_screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.aisummarizer.aisummarizer.R;
import com.example.aisummarizer.aisummarizer.adapters.SpinnerAdapter;
import com.example.aisummarizer.aisummarizer.aisummarizer.AISummarizerActivity;
import com.example.aisummarizer.aisummarizer.models.LangModel;
import com.example.aisummarizer.aisummarizer.service_calls.response_builder.SummarizerModel;
import com.example.aisummarizer.aisummarizer.super_class.SuperCompatActivity;
import com.example.aisummarizer.aisummarizer.utils.ApplicationHolder;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FullScreenActivity extends SuperCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView summarizeValueTv;
    Spinner spinnerPer;
    List<LangModel> perModels;
    LangModel selectedPercentage;
    List<SummarizerModel> summarizerModelsResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        summarizeValueTv = (TextView) findViewById(R.id.summarizeValueTv);
        spinnerPer = (Spinner) findViewById(R.id.spinnerPer);


        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }

        summarizerModelsResponse = new ArrayList<>();
        // get data via the key
        String value1 = extras.getString("S_Value");
//        String value2 = extras.getString("S_Object");
//        summarizerModelsResponse = extras.getParcelableArrayList("S_Object");
        summarizerModelsResponse = ApplicationHolder.summarizerModelList;
        if (value1 != null) {
            // do something with the data
            summarizeValueTv.setText(value1);
        }

        /*if (value2 != null) {
            //
            //1. Convert object to JSON string
            Gson gson = new Gson();
            Type summarizerListType = new TypeToken<ArrayList<SummarizerModel>>() {
            }.getType();

            summarizerModelsResponse = gson.fromJson(value2, summarizerListType);
            //System.out.println(json);
        }*/

        perModels = new ArrayList<>();
        perModels.add(new LangModel("1", "100.0f"));
        perModels.add(new LangModel("5", "95.0f"));
        perModels.add(new LangModel("10", "90.0f"));
        perModels.add(new LangModel("20", "80.0f"));
        perModels.add(new LangModel("30", "70.f"));
        perModels.add(new LangModel("40", "60.0f"));
        perModels.add(new LangModel("50", "50.0f"));
        perModels.add(new LangModel("60", "40.0f"));
        perModels.add(new LangModel("70", "30.0f"));
        perModels.add(new LangModel("80", "20.0f"));
        perModels.add(new LangModel("90", "10.0f"));
        perModels.add(new LangModel("100", "0.0f"));

        //Custom spinner
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(FullScreenActivity.this, perModels);
        spinnerPer.setAdapter(spinnerAdapter);

        spinnerPer.setOnItemSelectedListener(this);

        spinnerPer.setSelection(6);
        selectedPercentage = new LangModel("50", "50.0f");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedPercentage = perModels.get(position);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                StringBuilder builder = new StringBuilder();
                String prefix = " ";
                for (SummarizerModel model : summarizerModelsResponse) {
                    if (model.getLevel() != null)
                        if (Float.parseFloat(model.getLevel()) >= Float.parseFloat(selectedPercentage.getLanguageCode())) {
                            builder.append(model.getText());
                            builder.append(prefix);
                        }
                }
                summarizeValueTv.setText(builder.toString());
            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
