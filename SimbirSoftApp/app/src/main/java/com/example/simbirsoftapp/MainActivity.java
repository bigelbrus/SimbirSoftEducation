package com.example.simbirsoftapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends AppCompatActivity {
    private static final String LOG = MainActivity.class.getSimpleName();
    private static final int RECYCLER_VIEW_COLUMNS_NUMBER = 2;
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

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        RecyclerView rvHelpCategory = findViewById(R.id.rv_help);
        rvHelpCategory.setLayoutManager(new GridLayoutManager(this, RECYCLER_VIEW_COLUMNS_NUMBER));

        setActionBar(toolbar);

        newsButton = findViewById(R.id.button_news);
        searchButton = findViewById(R.id.button_search);
        helpButton = findViewById(R.id.button_help);
        historyButton = findViewById(R.id.button_history);
        profileButton = findViewById(R.id.button_profile);
        activeButton = newsButton;

    }

    public void onBottomButtonClick(View view) {
        switch (view.getId()) {
            case R.id.button_news:
                activateButton(newsButton);
                break;
            case R.id.button_help:
            case R.id.image_button_help:
                activateButton(helpButton);
                break;
            case R.id.button_search:
                activateButton(searchButton);
                break;
            case R.id.button_history:
                activateButton(historyButton);
                break;
            case R.id.button_profile:
                activateButton(profileButton);
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

    private void setActionBar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(null);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    private void activateButton(Button b) {
        activeButton.setActivated(false);
        b.setActivated(true);
        activeButton = b;
    }


}
