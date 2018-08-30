package com.example.aisummarizer.aisummarizer.service_calls;

import com.example.aisummarizer.aisummarizer.service_calls.response_builder.AuthResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AISummarizer {

    @GET("LoginJ?")
    Call<AuthResponse> login(@Query("email") String email,
                             @Query("password") String password);

    @GET("RegisterJ?")
    Call<AuthResponse> register(@Query("email") String email,
                                @Query("password") String password);
}
