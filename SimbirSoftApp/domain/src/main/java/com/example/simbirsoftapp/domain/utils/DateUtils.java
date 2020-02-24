package com.example.simbirsoftapp.domain.utils;

import org.threeten.bp.LocalDate;


public interface DateUtils {

    String formatDate(LocalDate date);

    LocalDate parseDate(String date);

}
