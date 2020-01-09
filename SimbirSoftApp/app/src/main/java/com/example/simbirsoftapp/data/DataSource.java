package com.example.simbirsoftapp.data;

import android.content.Context;

import com.example.simbirsoftapp.App;
import com.example.simbirsoftapp.R;
import com.example.simbirsoftapp.data.database.DatabaseSource;
import com.example.simbirsoftapp.data.database.RealmCategory;
import com.example.simbirsoftapp.data.database.RealmEvent;
import com.example.simbirsoftapp.data.model.Category;
import com.example.simbirsoftapp.data.model.Event;
import com.example.simbirsoftapp.data.model.User;
import com.example.simbirsoftapp.utility.RealmUtils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class DataSource {
    private static User user;
    private static DataSource instance;
    private Realm realm;
    private static final String FILE_NAME_CATEGORIES = "categories.json";
    private static final String FILE_NAME_EVENTS = "events.json";
    public static final String STANDARD_EVENT_IMAGE = "child";

    private DataSource(Realm realm) {
        this.realm = realm;
    }

    public static DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource(DatabaseSource.getInstance().getRealm());
        }
        return instance;
    }

    public List<Category> getCategories(Context context){
        realm = Realm.getInstance(RealmUtils.getDefaultConfig());
        if (realm.where(RealmCategory.class).findFirst() != null) {
            List<Category> categoriesFromDb = new ArrayList<>();
            try {
                RealmResults<RealmCategory> realmResults = realm.where(RealmCategory.class).findAll();
                for (RealmCategory c : realmResults) {
                    categoriesFromDb.add(new Category(c));
                }
                return categoriesFromDb;
            } finally {
                realm.close();
            }
        } else {
            return categoriesFromJson(context);
        }
    }

    public List<Event> getEvents(Context context){
        realm = Realm.getInstance(RealmUtils.getDefaultConfig());
        if (realm.where(RealmEvent.class).findFirst() != null) {
            List<Event> eventsFromDb = new ArrayList<>();
            try {
                RealmResults<RealmEvent> realmResults = realm.where(RealmEvent.class).findAll();
                for (RealmEvent e : realmResults) {
                    eventsFromDb.add(new Event(e));
                }
                return eventsFromDb;
            } finally {
                realm.close();
            }
        } else {
            return eventsFromJson(context);
        }
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


    private List<Category> categoriesFromJson(Context context) {
        List<Category> list = dataFromJson(context, FILE_NAME_CATEGORIES, App.categoryListType);
        realm.beginTransaction();
        for (Category c : list) {
            realm.copyToRealmOrUpdate(new RealmCategory(c));
        }
        realm.commitTransaction();
        realm.close();

        return list;
    }

    private List<Event> eventsFromJson(Context context) {
        List<Event> list = dataFromJson(context, FILE_NAME_EVENTS, App.eventsListType);
        realm.beginTransaction();
        for (Event e : list) {
            realm.copyToRealmOrUpdate(new RealmEvent(e));
        }
        realm.commitTransaction();
        realm.close();
        return list;
    }

    private static List dataFromJson(Context context, String name, Type type) {

        try (InputStream is = context.getAssets().open(name);
             InputStreamReader isr = new InputStreamReader(is)) {

            return App.gson.fromJson(isr, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList();
    }

}

