package com.example.simbirsoftapp.data.utils;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.DateTimeFormatterBuilder;


public class DateUtils {

    private DateUtils() {
    }
    private static DateTimeFormatter ddMMMMyyy = new DateTimeFormatterBuilder()
            .appendPattern("dd")
            .appendLiteral(' ')
            .appendPattern("MMMM")
            .appendLiteral(' ')
            .appendPattern("yyyy")
            .toFormatter();
    private static DateTimeFormatter ddMMyyyy = new DateTimeFormatterBuilder()
            .appendPattern("dd")
            .appendLiteral(' ')
            .appendPattern("MM")
            .appendLiteral(' ')
            .appendPattern("yyyy")
            .toFormatter();

    public static String formatDate(LocalDate date) {
        return date.format(ddMMMMyyy);
    }

    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, ddMMyyyy);
    }
}
