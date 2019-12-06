package com.example.simbirsoftapp;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;

public class App extends Application {
    @Override
    public void onCreate() {
        AndroidThreeTen.init(this);
        super.onCreate();
    }
}
