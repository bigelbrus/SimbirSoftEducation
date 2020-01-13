package com.example.simbirsoftapp.data;

import android.content.Context;
import android.util.Log;

import com.example.simbirsoftapp.App;
import com.example.simbirsoftapp.R;
import com.example.simbirsoftapp.data.database.DatabaseSource;
import com.example.simbirsoftapp.data.database.RealmCategory;
import com.example.simbirsoftapp.data.database.RealmEvent;
import com.example.simbirsoftapp.data.model.Category;
import com.example.simbirsoftapp.data.model.Event;
import com.example.simbirsoftapp.data.model.User;
import com.example.simbirsoftapp.utility.RealmUtils;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class DataSource {
    private static User user;
    private static DataSource instance;
    private Realm realm;
    private static final String FILE_NAME_CATEGORIES = "categories.json";
    private static final String FILE_NAME_EVENTS = "events.json";
    public static final String STANDARD_EVENT_IMAGE = "child";
    private static final long MAX_SIZE_BUFFER = 1024 * 1024L;
    private DataSource(Realm realm) {
        this.realm = realm;
    }

    public static DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource(DatabaseSource.getInstance().getRealm());
        }
        return instance;
    }

    public List<Category> getCategories(Context context) {
        List<Category> result = dataFromDb(RealmCategory.class,new Category());
        if (result.isEmpty()) {
            List<Category> fromFireBase = dataFromFireBase(App.categoryListType, FILE_NAME_CATEGORIES,RealmCategory.class);
            result = fromFireBase.isEmpty() ? categoriesFromJson(context) : fromFireBase;
        }
        return result;
    }

    public List<Event> getEvents(Context context) {
        List<Event> result = dataFromDb(RealmEvent.class,new Event());
        if (result.isEmpty()) {
            List<Event> fromFireBase = dataFromFireBase(App.eventsListType, FILE_NAME_EVENTS,RealmEvent.class);
            result = fromFireBase.isEmpty() ? eventsFromJson(context) : fromFireBase;
        }
        return result;
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
        saveToDb(RealmCategory.class,list);
        return list;
    }

    private List<Event> eventsFromJson(Context context) {
        List<Event> list = dataFromJson(context, FILE_NAME_EVENTS, App.eventsListType);
        saveToDb(RealmEvent.class,list);
        return list;
    }

    private static <T> List<T> dataFromJson(Context context, String name, Type type) {
        Log.d("tag","start retrieve data from local json");

        try (InputStream is = context.getAssets().open(name);
             InputStreamReader isr = new InputStreamReader(is)) {
            Log.d("tag","return data from local json");
            return App.gson.fromJson(isr, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private <T> List<T> dataFromFireBase(Type type, String location,Class<? extends RealmObject> realmObject) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference reference = storage.getReference(location);

        Object lock = new Object();

        final List<T> list = new ArrayList<>();
        Thread thread = new Thread(() -> {
            Log.d("tag", "start thread firebase");
            reference.getBytes(MAX_SIZE_BUFFER).addOnCompleteListener(task -> {
                synchronized (lock) {
                    Log.d("tag", "got result firebase");
                    InputStream is = new ByteArrayInputStream(task.getResult());
                    InputStreamReader isr = new InputStreamReader(is);
                    list.addAll(App.gson.fromJson(isr, type));
                    lock.notifyAll();
                }
            }).addOnFailureListener(exception -> {
                synchronized (lock) {
                    Log.d("tag", "fail");
                    lock.notifyAll();
                }
            });
        });
        thread.start();
        synchronized (lock) {
            if (list.isEmpty()) {
                try {
                    Log.d("tag", "wait");
                    lock.wait(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();

                    Thread.currentThread().interrupt();
                }
            }
            Log.d("tag","return data from firebase");
            saveToDb(realmObject,list);

            return list;
        }
    }

    private <T extends RealmObject, V> List<V> dataFromDb(Class<T> t, V type) {
        Log.d("tag","retrieve data from db");
        realm = Realm.getInstance(RealmUtils.getDefaultConfig());
        if (realm.where(t).findFirst() != null) {
            List objectsFromDb = new ArrayList<>();
            try {
                RealmResults<T> realmResults = realm.where(t).findAll();
                if (type instanceof Category) {
                    for (T c : realmResults) {
                        objectsFromDb.add(new Category((RealmCategory)c));
                    }
                } else if (type instanceof Event) {
                    for (T e : realmResults) {
                        objectsFromDb.add(new Event((RealmEvent) e));
                    }
                }
                Log.d("tag","return data from db");
                return objectsFromDb;
            } finally {
                realm.close();
            }
        }
        Log.d("tag","no data in db " + t.getSimpleName());
        return new ArrayList<>();
    }

    private void saveToDb(Class<? extends RealmObject> dbObject,List<?> list) {
        Log.d("tag","start save data to db " + dbObject.getName());
        realm = Realm.getInstance(RealmUtils.getDefaultConfig());
        realm.beginTransaction();
        Log.d("tag","Category? " + dbObject.isInstance(new RealmCategory()));
        Log.d("tag","Event? " + dbObject.isInstance(new RealmEvent()));

        if (dbObject.isInstance(new RealmCategory())) {
            Log.d("tag","save category to db");
            for (Object c : list) {
                realm.copyToRealmOrUpdate(new RealmCategory((Category) c));
            }
        } else if (dbObject.isInstance(new RealmEvent())) {
            Log.d("tag","save event to db");
            for (Object c : list) {
                realm.copyToRealmOrUpdate(new RealmEvent((Event) c));
            }
        }
        realm.commitTransaction();
        realm.close();
    }
}




