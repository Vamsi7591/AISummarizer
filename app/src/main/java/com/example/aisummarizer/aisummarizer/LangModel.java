package com.example.aisummarizer.aisummarizer;

public class LangModel {

    String language;

    String languageCode;

    public LangModel(String language, String languageCode) {
        this.language = language;
        this.languageCode = languageCode;
    }

    public LangModel() {

    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }
}
