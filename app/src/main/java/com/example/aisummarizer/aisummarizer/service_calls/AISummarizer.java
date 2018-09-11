package com.example.aisummarizer.aisummarizer.service_calls;

import com.example.aisummarizer.aisummarizer.service_calls.response_builder.AuthResponse;
import com.example.aisummarizer.aisummarizer.service_calls.response_builder.SummarizerModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AISummarizer {

    @GET("LoginJ?")
    Call<AuthResponse> login(@Query("email") String email,
                             @Query("password") String password);

    @GET("RegisterJ?")
    Call<AuthResponse> register(@Query("email") String email,
                                @Query("password") String password);

    @POST("TextSummarizer?")
    Call<List<SummarizerModel>> summarizerText(@Query("language") String language,
                                           @Query("style") String style,
                                           @Query("domains") String domains,
                                           @Query("content") String content);

    @POST("FileSummarizer?")
    Call<List<SummarizerModel>> summarizerFile(@Query("language") String language,
                                           @Query("style") String style,
                                           @Query("domains") String domains,
                                           @Query("content") String content);

    @POST("WebSummarizer?")
    Call<List<SummarizerModel>> summarizerWeb(@Query("language") String language,
                                           @Query("style") String style,
                                           @Query("domains") String domains,
                                           @Query("url") String url);
}
