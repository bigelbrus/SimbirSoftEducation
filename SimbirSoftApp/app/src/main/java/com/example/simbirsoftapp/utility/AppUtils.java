package com.example.simbirsoftapp.utility;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.simbirsoftapp.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import static com.example.simbirsoftapp.ui.search.SearchPagerAdapter.POSITION_EVENT;

public class AppUtils {

    private static Random random = new Random();


    private AppUtils() {
    }

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

    public static String[] findInArray(Context context, String query, int position) {
        List<String> result = new ArrayList<>();
        String[] allData;
        if (position == POSITION_EVENT) {
            allData = context.getResources().getStringArray(R.array.events_list);
        } else {
            allData = context.getResources().getStringArray(R.array.organisations_list);
        }
        String[] allDataForSearch = new String[allData.length];
        for (int i = 0; i < allData.length; i++) {
            allDataForSearch[i] = allData[i].toLowerCase();
        }
        for (int i = 0; i < allData.length; i++) {
            if (allDataForSearch[i].contains(query.toLowerCase())) {
                result.add(allData[i]);
            }
        }
        String[] resultArr = new String[result.size()];
        result.toArray(resultArr);
        return resultArr;
    }

    public static Drawable getDrawableByStringRes(Context context, String res) {
        Log.d("tag","res " + res);
        return context.getResources().getDrawable(context.getResources().getIdentifier(res,
                "drawable", context.getPackageName()));
    }

    public static Spannable getUnderlineGreenSpan(Context context, int textRes) {
        Spannable text = new SpannableString(context.getString(textRes));
        text.setSpan(new UnderlineSpan(), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.leaf)),
                0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return text;
    }
}
