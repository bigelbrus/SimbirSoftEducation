package com.example.simbirsoftapp;

import android.app.Application;
import android.util.Log;


import com.example.simbirsoftapp.di.component.ApplicationComponent;
import com.example.simbirsoftapp.di.component.DaggerApplicationComponent;
import com.example.simbirsoftapp.di.module.ApplicationModule;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jakewharton.threetenabp.AndroidThreeTen;


public class App extends Application {
    ApplicationComponent applicationComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        signIn(applicationComponent.firebaseAuth());

    }

    private void signIn(FirebaseAuth mAuth) {
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
