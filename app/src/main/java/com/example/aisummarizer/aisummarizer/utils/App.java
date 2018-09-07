package com.example.aisummarizer.aisummarizer.utils;

import android.app.Application;
import android.content.res.Configuration;

import java.util.Locale;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        setLocale("fr");

        // Create a new Locale object
        /*Locale locale = new Locale("fr");
        Locale.setDefault(locale);
        // Create a new configuration object
        Configuration config = new Configuration();
        // Set the locale of the new configuration
        config.locale = locale;
        // Update the configuration of the Application context
        getResources().updateConfiguration(
                config,
                getResources().getDisplayMetrics()
        );*/
    }

    private void setLocale(String locale) {
        Locale jaLocale = new Locale(locale);
        LangUtils.setLocale(jaLocale);
        LangUtils.setConfigChange(this);
    }
}
