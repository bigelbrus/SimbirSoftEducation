package com.example.simbirsoftapp.di.component;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simbirsoftapp.di.PerActivity;
import com.example.simbirsoftapp.di.module.ActivityModule;
import com.google.firebase.auth.FirebaseAuth;

import dagger.Component;

@PerActivity
@Component (dependencies = ApplicationComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    AppCompatActivity activity();
    FirebaseAuth firebaseAuth();
}
