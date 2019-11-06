package com.example.simbirsoftapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class MainActivity extends AppCompatActivity {
    private static final int HELP_ITEM_BOTTOM_ID = 2;
    private static final int RECYCLER_VIEW_COLUMNS_NUMBER = 2;
    private static final int BOTTOM_ICON_SIZE = 40;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationViewEx bottomNavigation = findViewById(R.id.bottom_navigation_view);
        FloatingActionButton floatingActionButton = findViewById(R.id.fab_help);
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        RecyclerView rvHelpCategory = findViewById(R.id.rv_help);
        rvHelpCategory.setLayoutManager(new GridLayoutManager(this,RECYCLER_VIEW_COLUMNS_NUMBER));

        setBottomNavigationView(bottomNavigation);
        setActionBar(toolbar);


        floatingActionButton.setOnClickListener(click -> {
            bottomNavigation.setCurrentItem(HELP_ITEM_BOTTOM_ID);
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setBottomNavigationView(BottomNavigationViewEx bottomNavigation) {
        bottomNavigation.setIconSize(BOTTOM_ICON_SIZE)
                .setIconsMarginTop(0);
    }

    private void setActionBar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(null);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }


}
