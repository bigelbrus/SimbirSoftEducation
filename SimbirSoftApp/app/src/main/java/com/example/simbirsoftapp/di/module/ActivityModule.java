package com.example.simbirsoftapp.di.module;

import android.app.Activity;

import com.example.simbirsoftapp.di.PerActivity;

import dagger.Module;
import dagger.Provides;


@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @PerActivity
    @Provides
    public Activity activity(){
        return activity;
    }
}
