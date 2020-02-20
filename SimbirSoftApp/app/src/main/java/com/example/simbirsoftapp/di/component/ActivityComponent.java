package com.example.simbirsoftapp.di.component;

import android.app.Activity;

import com.example.simbirsoftapp.di.PerActivity;
import com.example.simbirsoftapp.di.module.ActivityModule;

import dagger.Component;

@PerActivity
@Component (dependencies = ApplicationComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    Activity activity();
}
