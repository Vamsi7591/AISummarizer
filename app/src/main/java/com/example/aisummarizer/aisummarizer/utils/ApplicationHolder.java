package com.example.aisummarizer.aisummarizer.utils;

import com.example.aisummarizer.aisummarizer.service_calls.response_builder.SummarizerModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sathish on 09-Sep-16.
 */

public class ApplicationHolder {

    public static final String Logcat = "O";
    public static List<SummarizerModel> summarizerModelList = new ArrayList<>();

    public static final String baseUrl = "https://aisummarizer.com/search2summarize/";
    public static final String baseUrl2 = "http://dev.aisummarizer.com/summarizer/online/";

    /**
     * https://aisummarizer.com/search2summarize/
     * LoginJ
     * ?
     * email
     * =
     * shami@maily.com
     * &
     * password
     * =
     * 12345*/
}


