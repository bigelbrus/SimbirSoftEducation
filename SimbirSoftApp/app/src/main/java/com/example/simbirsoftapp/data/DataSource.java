package com.example.simbirsoftapp.data;

import android.content.Context;
import android.util.Log;

import com.example.simbirsoftapp.R;
import com.example.simbirsoftapp.data.model.Category;
import com.example.simbirsoftapp.data.model.Event;
import com.example.simbirsoftapp.data.model.User;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DataSource {
    private static User user;
    private static List<Category> categories;
    private static List<Event> events;

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



    public static List<User> getFriends() {
        List<User> friends = new ArrayList<>();
        friends.add(new User("Дмитрий", "Валерьевич", R.drawable.avatar_3));
        friends.add(new User("Евгений", "Александров", R.drawable.avatar_2));
        friends.add(new User("Виктор", "Кузнецов", R.drawable.avatar_1));
        friends.add(new User("Иван", "Петров", R.drawable.avatar_3));
        return friends;
    }

    public static User getUser() {
        if (user == null) {
            user = new User("Денис", "Константинов", "Хирургия, травмвтология",
                    true, R.drawable.image_man, DataSource.getFriends(), "01 02 1980");
        }
        return user;
    }


    private static List<Category> categoriesFromJson(Context context) {
        String name = "categories.json";
        try (InputStream fis = context.getAssets().open(name);
             InputStreamReader isr = new InputStreamReader(fis)) {
            Gson gson = new Gson();
            DataItems dataItems = gson.fromJson(isr, DataItems.class);
            return dataItems.getCategories();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private static List<Event> eventsFromJson(Context context) {
        String name = "events.json";
        try (InputStream is = context.getAssets().open(name);
             InputStreamReader isr = new InputStreamReader(is)) {
            Gson gson = new Gson();
            DataItems items = gson.fromJson(isr, DataItems.class);
            return items.getEvents();
        } catch (Exception e) {
            Log.d("tag",e.getMessage());
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private static class DataItems {
        private List<Category> categories;
        private List<Event> events;

        public List<Category> getCategories() {
            return categories;
        }

        public List<Event> getEvents() {
            return events;
        }

        public void setCategories(List<Category> categories) {
            this.categories = categories;
        }
    }

}

