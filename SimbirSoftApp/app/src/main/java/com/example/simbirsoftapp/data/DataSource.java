package com.example.simbirsoftapp.data;

import android.content.Context;

import com.example.simbirsoftapp.App;
import com.example.simbirsoftapp.R;
import com.example.simbirsoftapp.data.model.Category;
import com.example.simbirsoftapp.data.model.Event;
import com.example.simbirsoftapp.data.model.User;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataSource {
    private static User user;
    private static List<Category> categories;
    private static List<Event> events;
    private static final String FILE_NAME_CATEGORIES = "categories.json";
    private static final String FILE_NAME_EVENTS = "events.json";
    public static final String STANDARD_EVENT_IMAGE = "child";

    private DataSource() {
    }

    public static List<Category> getCategories(Context context) {
        if (categories == null) {
            categories = categoriesFromJson(context);
        }
        return categories;
    }

    public static List<Event> getEvents(Context context) {
        if (events == null) {
            events = eventsFromJson(context);
        }
        return events;
    }

    public static User getUser() {
        if (user == null) {
            user = new User("Денис", "Константинов", "Хирургия, травмвтология",
                    true, R.drawable.image_man,
                    DataSource.getFriends(), "01 02 1980");
        }
        return user;
    }

    private static List<User> getFriends() {
        List<User> friends = new ArrayList<>();
        friends.add(new User("Дмитрий", "Валерьевич", R.drawable.avatar_3));
        friends.add(new User("Евгений", "Александров", R.drawable.avatar_2));
        friends.add(new User("Виктор", "Кузнецов", R.drawable.avatar_1));
        friends.add(new User("Иван", "Петров", R.drawable.avatar_3));
        return friends;
    }


    private static List<Category> categoriesFromJson(Context context) {
        return dataFromJson(context,FILE_NAME_CATEGORIES,App.categoryListType);
    }

    private static List<Event> eventsFromJson(Context context) {
        return dataFromJson(context,FILE_NAME_EVENTS,App.eventsListType);
    }


    private static List dataFromJson(Context context, String name, Type type ) {
        try (InputStream is = context.getAssets().open(name);
             InputStreamReader isr = new InputStreamReader(is)) {
            return App.gson.fromJson(isr, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


}

