package com.example.aisummarizer.aisummarizer.service_calls.request_builder;

public class SummarizerRequest {

    private String language;
    private String style;
    private String domains;
    private String content;
    private String url;

    public SummarizerRequest(String language, String style, String domains, String content) {
        this.language = language;
        this.style = style;
        this.domains = domains;
        this.content = content;
    }

    public SummarizerRequest(String language, String style, String domains, String content, String url) {
        this.language = language;
        this.style = style;
        this.domains = domains;
        this.content = content;
        this.url = url;
    }

    public SummarizerRequest() {
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getDomains() {
        return domains;
    }

    public void setDomains(String domains) {
        this.domains = domains;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
