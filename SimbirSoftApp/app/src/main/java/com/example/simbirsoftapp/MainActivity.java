package com.example.simbirsoftapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.simbirsoftapp.ui.help.HelpFragment;
import com.example.simbirsoftapp.ui.profile.ProfileFragment;
import com.example.simbirsoftapp.ui.search.SearchFragment;


public class MainActivity extends AppCompatActivity {
    private Button newsButton;
    private Button searchButton;
    private Button helpButton;
    private Button historyButton;
    private Button profileButton;
    private Button activeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsButton = findViewById(R.id.button_news);
        searchButton = findViewById(R.id.button_search);
        helpButton = findViewById(R.id.button_help);
        historyButton = findViewById(R.id.button_history);
        profileButton = findViewById(R.id.button_profile);
        activeButton = helpButton;

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_main);
        if (fragment == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_main, new HelpFragment()).commit();
        }

        activeButton.setActivated(true);

    }

    public void onBottomButtonClick(View view) {
        switch (view.getId()) {
            case R.id.button_news:
                activateButton(newsButton);
                break;
            case R.id.button_help:
            case R.id.image_button_help:
                activateButton(helpButton);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_main, new HelpFragment())
                        .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
                break;
            case R.id.button_search:
                activateButton(searchButton);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_main,
                        new SearchFragment())
                        .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
                break;
            case R.id.button_history:
                activateButton(historyButton);
                break;
            case R.id.button_profile:
                activateButton(profileButton);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_main,
                        new ProfileFragment())
                        .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
                break;
            default:
                break;
        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void activateButton(Button b) {
        activeButton.setActivated(false);
        b.setActivated(true);
        activeButton = b;
    }


}
