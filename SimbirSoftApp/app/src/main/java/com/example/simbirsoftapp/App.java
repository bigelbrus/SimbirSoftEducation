package com.example.simbirsoftapp;

import android.app.Application;
import android.util.Log;


import com.example.simbirsoftapp.data.adapter.CategoryTypeAdapter;
import com.example.simbirsoftapp.data.adapter.EventTypeAdapter;
import com.example.simbirsoftapp.data.model.Category;
import com.example.simbirsoftapp.data.model.Event;
import com.example.simbirsoftapp.di.component.ApplicationComponent;
import com.example.simbirsoftapp.di.component.DaggerApplicationComponent;
import com.example.simbirsoftapp.di.module.ApplicationModule;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.jakewharton.threetenabp.AndroidThreeTen;

import java.lang.reflect.Type;
import java.util.List;

import io.realm.Realm;

public class App extends Application {
    ApplicationComponent applicationComponent;


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
        super.onCreate();
        Realm.init(this);
        AndroidThreeTen.init(this);
        signIn();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    private void signIn() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Log.d("tag", "signInAnonymously complete");
                } else {
                    Log.d("tag", "signInAnonymously error " + task.getException().getMessage());
                }
            });
        }
        else {
            Log.d("tag", "is anonymous " + user.isAnonymous());
        }
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
