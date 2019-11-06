package com.example.simbirsoftapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {
    private static final long MAIN_ACTIVITY_START_DELAY = 2000l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        delayMainActivity();
    }

    private void delayMainActivity(){
        Intent mainActivityIntent = new Intent(this,MainActivity.class);
        new Handler().postDelayed(()->{
            startActivity(mainActivityIntent);
            finish();
        },MAIN_ACTIVITY_START_DELAY);

    }
}
