package com.example.simbirsoftapp;

import android.app.Application;
import android.util.Log;


import com.example.simbirsoftapp.data.adapter.CategoryTypeAdapter;
import com.example.simbirsoftapp.data.adapter.EventTypeAdapter;
import com.example.simbirsoftapp.data.model.Category;
import com.example.simbirsoftapp.data.model.Event;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.jakewharton.threetenabp.AndroidThreeTen;

import java.lang.reflect.Type;
import java.util.List;

import io.realm.Realm;

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
        Realm.init(this);
        AndroidThreeTen.init(this);
        super.onCreate();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInAnonymously().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.d("tag", "auth complete");
            } else {

                Log.d("tag", "auth error " + task.getException().getMessage());
            }
        });
    }
}
