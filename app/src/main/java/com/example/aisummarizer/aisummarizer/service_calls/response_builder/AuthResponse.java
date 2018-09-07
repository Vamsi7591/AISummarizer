package com.example.aisummarizer.aisummarizer.service_calls.response_builder;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

/**
 * Response model for
 * Login and Registration*/
public class AuthResponse {


    @SerializedName("success")
    public boolean success;


    @SerializedName("description")
    public String description;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(  boolean success) {
        this.success = success;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(  String description) {
        this.description = description;
    }
}
