package com.example.simbirsoftapp.data;

import android.content.Context;
import android.util.Log;

import com.example.simbirsoftapp.App;
import com.example.simbirsoftapp.R;
import com.example.simbirsoftapp.data.database.DatabaseSource;
import com.example.simbirsoftapp.data.database.RealmCategory;
import com.example.simbirsoftapp.data.database.RealmEvent;
import com.example.simbirsoftapp.data.firebase.RxFirebaseClass;
import com.example.simbirsoftapp.data.model.Category;
import com.example.simbirsoftapp.data.model.Event;
import com.example.simbirsoftapp.data.model.User;
import com.example.simbirsoftapp.utility.RealmUtils;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
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

    private DataSource(Realm realm) {
        this.realm = realm;
    }

    public static DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource(DatabaseSource.getInstance().getRealm());
        }
        return instance;
    }

    public Flowable<Category> getCategories(Context context) {
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
        return Flowable.empty();
    }

    public Flowable<Event> getEvents(Context context) {
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
        return Flowable.empty();
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


    private Flowable<Category> categoriesFromJson(Context context) throws NoSuchDataException {
        return dataFromJson(context, FILE_NAME_CATEGORIES, App.categoryListType);
    }

    private Flowable<Event> eventsFromJson(Context context) throws NoSuchDataException {
        return dataFromJson(context, FILE_NAME_EVENTS, App.eventsListType);
    }

    private static <T> Flowable<T> dataFromJson(Context context, String name, Type type) throws
            NoSuchDataException {
        InputStream is = null;
        try {
            is = context.getAssets().open(name);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new NoSuchDataException("No file in " + name + " folder in local json");
        }
        return Flowable.just(is)
                .subscribeOn(Schedulers.io())
                .map(InputStreamReader::new)
                .map(isr -> {
                    List<T> list = App.gson.fromJson(isr, type);
                    saveToDb(list);
                    return list;
                })
                .flatMapIterable(list -> list)
                .observeOn(AndroidSchedulers.mainThread());
    }

    private <T> Flowable<T> dataFromFireBase(Type type, String location) throws
            NoSuchDataException {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference reference = storage.getReference(location);
        return RxFirebaseClass.getBytes(reference, MAX_SIZE_BUFFER)
                .observeOn(Schedulers.io())
                .toFlowable()
                .map(ByteArrayInputStream::new)
                .map(InputStreamReader::new)
                .map(inputStreamReader -> {
                    List<T> list = App.gson.fromJson(inputStreamReader, type);
                    saveToDb(list);
                    return list; })
                .flatMapIterable(list -> list)
                .observeOn(AndroidSchedulers.mainThread());

    }

    private <V> Flowable dataFromDb(V type) throws NoSuchDataException {
        try {
            realm = Realm.getInstance(RealmUtils.getDefaultConfig());
            if (type instanceof Category && realm.where(RealmCategory.class).findFirst() != null) {
                return realm.where(RealmCategory.class).findAllAsync()
                        .asFlowable()
                        .filter(RealmResults::isLoaded)
                        .flatMap(Flowable::fromIterable)
                        .flatMap(realmCategory -> Flowable.just(new Category(realmCategory)));
            } else if (type instanceof Event && realm.where(RealmEvent.class).findFirst() != null) {
                return realm.where(RealmEvent.class).findAllAsync()
                        .asFlowable()
                        .filter(RealmResults::isLoaded)
                        .flatMap(Flowable::fromIterable)
                        .flatMap(realmEvent -> {
                            Log.d("tag", realmEvent.getEventCompany());
                            return Flowable.just(new Event(realmEvent));
                        });
            }
            throw new NoSuchDataException("No " + type + " data in database");
        } finally {
            realm.close();
        }
    }

    private static void saveToDb(List<?> list) {
        if (list.isEmpty()) {
            return;
        }
        Realm realm = Realm.getInstance(RealmUtils.getDefaultConfig());
        realm.beginTransaction();

        if (list.get(0) instanceof Category) {
            for (Object c : list) {
                Log.d("tag","list category to db " + ((Category)c).getText());
                realm.copyToRealmOrUpdate(new RealmCategory((Category) c));
            }
        } else if (list.get(0) instanceof Event) {
            for (Object c : list) {
                Log.d("tag","list event to db " + ((Event)c).getEventCompany());
                realm.copyToRealmOrUpdate(new RealmEvent((Event) c));
            }
        }
        realm.commitTransaction();
        realm.close();
    }

}




