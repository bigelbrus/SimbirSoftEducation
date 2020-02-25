package com.example.simbirsoftapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.simbirsoftapp.databinding.ActivityMainBinding;
import com.example.simbirsoftapp.di.component.ApplicationComponent;
import com.example.simbirsoftapp.di.component.CategoryComponent;
import com.example.simbirsoftapp.di.component.DaggerCategoryComponent;
import com.example.simbirsoftapp.di.module.ActivityModule;
import com.example.simbirsoftapp.ui.auth.AuthFragment;
import com.example.simbirsoftapp.ui.help.HelpFragment;
import com.example.simbirsoftapp.ui.news.NewsFragment;
import com.example.simbirsoftapp.ui.profile.ProfileFragment;
import com.example.simbirsoftapp.ui.search.SearchFragment;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity {
    private static final String KEY_ACTIVE_BUTTON = "ACTIVE_BUTTON";
    private Button newsButton;
    private Button searchButton;
    private Button helpButton;
    private Button historyButton;
    private Button profileButton;
    private Button activeButton;
    private ActivityMainBinding mainBinding;
    View bottomPanel;
    CategoryComponent categoryComponent;
    @Inject
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);
        initInjectors();
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        newsButton = mainBinding.bottomPanel.buttonNews;
        searchButton = mainBinding.bottomPanel.buttonSearch;
        helpButton = mainBinding.bottomPanel.buttonHelp;
        historyButton = mainBinding.bottomPanel.buttonHistory;
        profileButton = mainBinding.bottomPanel.buttonProfile;
        bottomPanel = mainBinding.bottomPanel.getRoot();

        if (savedInstanceState != null) {
            activeButton = findViewById(savedInstanceState.getInt(KEY_ACTIVE_BUTTON));
        } else {
            activeButton = helpButton;
        }

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_main);
        if (fragment == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_main, HelpFragment.newInstance()).commit();
        }
        activeButton.setActivated(true);

    }

    public void onBottomButtonClick(View view) {
        if (view.getId() == activeButton.getId()) {
            return;
        }
        switch (view.getId()) {
            case R.id.button_news:
                activateButton(newsButton);
                replaceFragment(NewsFragment.newInstance());
                break;
            case R.id.button_help:
            case R.id.image_button_help:
                activateButton(helpButton);
                replaceFragment(HelpFragment.newInstance());
                break;
            case R.id.button_search:
                activateButton(searchButton);
                replaceFragment(SearchFragment.newInstance());
                break;
            case R.id.button_history:
                activateButton(historyButton);
                break;
            case R.id.button_profile:
                activateButton(profileButton);
                if (firebaseAuth.getCurrentUser() == null ||
                        firebaseAuth.getCurrentUser().isAnonymous()) {
                    replaceFragment(AuthFragment.newInstance());
                } else {
                    replaceFragment(ProfileFragment.newInstance());
                }
                break;
            default:
                break;
        }
    }

    public CategoryComponent getCategoryComponent() {
        return categoryComponent;
    }

    public View getBottomPanel() {
        return bottomPanel;
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

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(KEY_ACTIVE_BUTTON, activeButton.getId());
        super.onSaveInstanceState(outState);
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((App) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    private void initInjectors(){
        categoryComponent = DaggerCategoryComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_main,
                fragment)
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

}
