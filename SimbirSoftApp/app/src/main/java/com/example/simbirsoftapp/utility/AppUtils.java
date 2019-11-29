package com.example.simbirsoftapp.utility;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.simbirsoftapp.R;

import java.util.HashSet;
import java.util.Random;

public class AppUtils {

    private static Random random = new Random();


    private AppUtils(){}

    public static void setActionBar(AppCompatActivity activity, View view, int titleRes, boolean setUpButton) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(null);
            actionBar.setDisplayHomeAsUpEnabled(setUpButton);
        }
        TextView toolbarTitle = view.findViewById(R.id.toolbar_title);
        toolbarTitle.setText(activity.getText(titleRes));
    }

    public static String[] getRandomStringArray(Context context, int size, int arrRes) {
        String[] allOrganizations = context.getResources().getStringArray(arrRes);
        size = (size > allOrganizations.length) ? allOrganizations.length : size;
        HashSet<Integer> set = new HashSet<>(size);
        int currentRandom;
        String[] randomOrganizations = new String[size];
        int i = 0;
        while (true) {
            do {
                currentRandom = random.nextInt(allOrganizations.length);
            }
            while (set.contains(currentRandom));
            set.add(currentRandom);
            randomOrganizations[i++] = allOrganizations[currentRandom];
            if (i == size) {
                break;
            }
        }
        return randomOrganizations;
    }
}
