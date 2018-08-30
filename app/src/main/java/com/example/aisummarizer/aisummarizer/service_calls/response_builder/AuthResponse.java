package com.example.aisummarizer.aisummarizer.service_calls.response_builder;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

/**
 * Response model for
 * Login and Registration*/
public class AuthResponse {

    @Nullable
    @SerializedName("success")
    public boolean success;

    @Nullable
    @SerializedName("description")
    public String description;

    @Nullable
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(@Nullable boolean success) {
        this.success = success;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }
}
