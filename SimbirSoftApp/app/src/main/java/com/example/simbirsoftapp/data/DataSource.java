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
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.realm.Realm;
import io.realm.RealmResults;

public class DataSource {
    private static User user;
    private static DataSource instance;
    private Realm realm;
    private static final String FILE_NAME_CATEGORIES = "categories.json";
    private static final String FILE_NAME_EVENTS = "events.json";
    public static final String STANDARD_EVENT_IMAGE = "child";
    private static final long MAX_SIZE_BUFFER = 1024 * 1024L;
    private static final String TAG = "DataSource";
    private static final long TIMEOUT_TIME = 5L;

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
        try {
            return dataFromDb(new Category());
        } catch (NoSuchDataException e) {
            Log.d(TAG, e.toString());
            try {
                return dataFromFireBase(App.categoryListType, FILE_NAME_CATEGORIES);
            } catch (NoSuchDataException ex) {
                Log.d(TAG, ex.toString());
                try {
                    return categoriesFromJson(context);
                } catch (NoSuchDataException exc) {
                    Log.d(TAG, exc.toString());
                }
            }
        }
        return new ArrayList<>();
    }

    public List<Event> getEvents(Context context) {
        try {
            return dataFromDb(new Event());
        } catch (NoSuchDataException e) {
            Log.d(TAG, e.toString());
            try {
                return dataFromFireBase(App.eventsListType, FILE_NAME_EVENTS);
            } catch (NoSuchDataException ex) {
                Log.d(TAG, ex.toString());
                try {
                    return eventsFromJson(context);
                } catch (NoSuchDataException exc) {
                    Log.d(TAG, exc.toString());
                }
            }
        }
        return new ArrayList<>();
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


    private List<Category> categoriesFromJson(Context context) throws NoSuchDataException {
        List<Category> list = dataFromJson(context, FILE_NAME_CATEGORIES, App.categoryListType);
        saveToDb(list);
        return list;
    }

    private List<Event> eventsFromJson(Context context) throws NoSuchDataException {
        List<Event> list = dataFromJson(context, FILE_NAME_EVENTS, App.eventsListType);
        saveToDb(list);
        return list;
    }

    private static <T> List<T> dataFromJson(Context context, String name, Type type) throws NoSuchDataException {
        try (InputStream is = context.getAssets().open(name);
             InputStreamReader isr = new InputStreamReader(is)) {
            return App.gson.fromJson(isr, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new NoSuchDataException("No " + type + " in local json");
    }

    private <T> List<T> dataFromFireBase(Type type, String location) throws NoSuchDataException {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference reference = storage.getReference(location);
        try {
            byte[] bytes = Tasks.await(reference.getBytes(MAX_SIZE_BUFFER),TIMEOUT_TIME,TimeUnit.SECONDS);
            InputStream is = new ByteArrayInputStream(bytes);
            InputStreamReader isr = new InputStreamReader(is);
            List<T> list = App.gson.fromJson(isr, type);
            saveToDb(list);
            return list;
        } catch (ExecutionException e) {
            Thread.currentThread().interrupt();
            throw new NoSuchDataException("No data in location: " + location + " in firebase");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new NoSuchDataException("Query to firebase was interrupt");
        } catch (TimeoutException e) {
            Thread.currentThread().interrupt();
            throw new NoSuchDataException("Too long request, more than " + TIMEOUT_TIME +"Cegt seconds");
        }
    }

    private <V> List<V> dataFromDb(V type) throws NoSuchDataException {
        try {
            realm = Realm.getInstance(RealmUtils.getDefaultConfig());
            if (type instanceof Category && realm.where(RealmCategory.class).findFirst() != null) {
                List categoriesFromDb = new ArrayList<>();
                RealmResults<RealmCategory> realmResults = realm.where(RealmCategory.class).findAll();
                for (RealmCategory c : realmResults) {
                    categoriesFromDb.add(new Category(c));
                }
                return categoriesFromDb;
            } else if (type instanceof Event && realm.where(RealmEvent.class).findFirst() != null) {
                List eventsFromDb = new ArrayList<>();
                RealmResults<RealmEvent> realmResults = realm.where(RealmEvent.class).findAll();
                for (RealmEvent c : realmResults) {
                    eventsFromDb.add(new Event(c));
                }
                return eventsFromDb;
            }
        } finally {
            realm.close();
        }

        throw new NoSuchDataException("No " + type + " data in database");
    }

    private void saveToDb(List<?> list) {
        if (list.isEmpty()) {
            return;
        }
        realm = Realm.getInstance(RealmUtils.getDefaultConfig());
        realm.beginTransaction();

        if (list.get(0) instanceof Category) {
            for (Object c : list) {
                realm.copyToRealmOrUpdate(new RealmCategory((Category) c));
            }
        } else if (list.get(0) instanceof Event) {
            for (Object c : list) {
                realm.copyToRealmOrUpdate(new RealmEvent((Event) c));
            }
        }
        realm.commitTransaction();
        realm.close();
    }
}




