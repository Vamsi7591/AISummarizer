package com.example.aisummarizer.aisummarizer.service_calls.response_builder;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

// to prevent specified fields from being serialized or deserialized
// (i.e. not include in JSON output; or being set even if they were included)
//@JsonIgnoreProperties({"word-count", "char-count", "weight"})

// To ignore any unknown properties in JSON input without exception:
//@JsonIgnoreProperties(ignoreUnknown=true)
public class SummarizerModel {


    @SerializedName("number")
    private String number;

    /*@JsonIgnore
    @SerializedName("word-count")
    private String wordCount;

    @JsonIgnore
    @SerializedName("char-count")
    private String charCount;*/

    @SerializedName("level")
    private String level;

    @SerializedName("text")
    private String text;

    @SerializedName("terminology")
    private String[] terminology;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    /*public String getWordCount() {
        return wordCount;
    }

    public void setWordCount(  String wordCount) {
        this.wordCount = wordCount;
    }


    public String getCharCount() {
        return charCount;
    }

    public void setCharCount(  String charCount) {
        this.charCount = charCount;
    }*/


    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public String[] getTerminology() {
        return terminology;
    }

    public void setTerminology(String[] terminology) {
        this.terminology = terminology;
    }
}
