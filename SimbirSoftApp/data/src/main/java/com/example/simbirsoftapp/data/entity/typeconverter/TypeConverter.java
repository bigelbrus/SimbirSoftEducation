package com.example.simbirsoftapp.data.entity.typeconverter;

import com.example.simbirsoftapp.data.entity.UserEntity;
import com.example.simbirsoftapp.data.utils.DateUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.threeten.bp.LocalDate;

import java.util.List;

public class TypeConverter {
    @androidx.room.TypeConverter
    public static String listStringToString(List<String> list) {
        return new Gson().toJson(list);
    }

    @androidx.room.TypeConverter
    public static List<String> fromStringToListString(String string) {
        return new Gson().fromJson(string, new TypeToken<List<String>>() {}.getType());
    }

    @androidx.room.TypeConverter
    public static String listUserEntityToString(List<UserEntity>list) {
        return new Gson().toJson(list);
    }

    @androidx.room.TypeConverter
    public static List<UserEntity> fromStringToUserEntity(String string) {
        return new Gson().fromJson(string,new TypeToken<List<UserEntity>>() {}.getType());
    }

    @androidx.room.TypeConverter
    public static String localDateToString (LocalDate date) {
        return DateUtils.formatDate(date);
    }

    @androidx.room.TypeConverter
    public static LocalDate fromStringToLocalDate (String date) {
        return DateUtils.parseDate(date);
    }
}
