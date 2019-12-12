package com.example.simbirsoftapp;

import android.app.Application;

import com.example.simbirsoftapp.data.adapter.CategoryTypeAdapter;
import com.example.simbirsoftapp.data.adapter.EventTypeAdapter;
import com.example.simbirsoftapp.data.model.Category;
import com.example.simbirsoftapp.data.model.Event;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.jakewharton.threetenabp.AndroidThreeTen;

import java.lang.reflect.Type;
import java.util.List;

public class App extends Application {
    public static final Type eventsListType = new TypeToken<List<Event>>() {
    }.getType();
    public static final Type categoryListType = new TypeToken<List<Category>>() {
    }.getType();
    public static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(categoryListType, new CategoryTypeAdapter())
            .registerTypeAdapter(eventsListType, new EventTypeAdapter())
            .create();

    @Override
    public void onCreate() {
        AndroidThreeTen.init(this);
        super.onCreate();
    }
}
