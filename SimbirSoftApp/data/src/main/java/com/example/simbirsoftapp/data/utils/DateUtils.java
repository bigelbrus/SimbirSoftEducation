package com.example.simbirsoftapp.data.utils;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.DateTimeFormatterBuilder;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DateUtils implements com.example.simbirsoftapp.domain.utils.DateUtils {
    @Inject
    public DateUtils() {}
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

    public String formatDate(LocalDate date) {
        return date.format(ddMMMMyyy);
    }

    public   LocalDate parseDate(String date) {
        return LocalDate.parse(date, ddMMyyyy);
    }
}
