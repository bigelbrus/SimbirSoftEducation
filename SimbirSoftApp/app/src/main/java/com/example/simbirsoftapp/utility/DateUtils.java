package com.example.simbirsoftapp.utility;


import android.util.Log;

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

}
