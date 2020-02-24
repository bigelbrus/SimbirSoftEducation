package com.example.simbirsoftapp.di.module;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simbirsoftapp.di.PerActivity;

import dagger.Module;
import dagger.Provides;


@Module
public class ActivityModule {
    private final AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @PerActivity
    @Provides
    public AppCompatActivity activity(){
        return activity;
    }
}
