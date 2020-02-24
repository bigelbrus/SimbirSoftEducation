package com.example.simbirsoftapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.simbirsoftapp.MainActivity;
import com.example.simbirsoftapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {
    private static final long MAIN_ACTIVITY_START_DELAY = 2000L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        delayMainActivity();
    }

    private void delayMainActivity() {
        Intent mainActivityIntent = new Intent(this, MainActivity.class);
        new Handler().postDelayed(() -> {
            startActivity(mainActivityIntent);
            finish();
        }, MAIN_ACTIVITY_START_DELAY);

    }

}
