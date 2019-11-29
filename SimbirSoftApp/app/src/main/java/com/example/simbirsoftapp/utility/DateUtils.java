package com.example.simbirsoftapp.utility;


import android.util.Log;

import com.example.simbirsoftapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    private DateUtils() {}

    public static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy",
                Locale.getDefault());
        return dateFormat.format(date);
    }


    public static Date parseDate(String date) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy",
            Locale.getDefault());
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
                Log.d("DateUtils",e.getMessage());
        }
        return new Date();
    }

    private static int getMonthRes(int m) {
        switch (m) {
            case 1:
                return R.string.january;
            case 2:
                return R.string.february;
            case 3:
                return R.string.march;
            case 4:
                return R.string.april;
            case 5:
                return R.string.may;
            case 6:
                return R.string.june;
            case 7:
                return R.string.july;
            case 8:
                return R.string.august;
            case 9:
                return R.string.september;
            case 10:
                return R.string.october;
            case 11:
                return R.string.november;
            case 12:
                return R.string.december;
            default:
                return 0;
        }
    }
}
