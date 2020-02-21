package com.example.simbirsoftapp.data.net;

import com.example.simbirsoftapp.data.entity.CategoryEntity;
import com.example.simbirsoftapp.data.entity.EventEntity;
import com.example.simbirsoftapp.data.entity.adapter.CategoryTypeAdapter;
import com.example.simbirsoftapp.data.entity.adapter.EventTypeAdapter;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
@Singleton
public class FirebaseImpl implements NetApi {
    private static final long MAX_SIZE_BUFFER = 1024 * 1024L;
    private static final Type categoryListType = new TypeToken<List<CategoryEntity>>() {
    }.getType();
    public static final Type eventsListType = new TypeToken<List<EventEntity>>() {
    }.getType();
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(categoryListType,new CategoryTypeAdapter())
            .registerTypeAdapter(eventsListType,new EventTypeAdapter())
            .create();
    @Inject
    public FirebaseImpl(){}

    @Override
    public Flowable<CategoryEntity> category() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference reference = storage.getReference("categories.json");
        return RxFirebaseClass.getBytes(reference, MAX_SIZE_BUFFER)
                .observeOn(Schedulers.io())
                .toFlowable()
                .map(ByteArrayInputStream::new)
                .map(InputStreamReader::new)
                .map(inputStreamReader -> (List<CategoryEntity>)gson.fromJson(inputStreamReader, categoryListType))
                .flatMapIterable(list -> list)
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Flowable<EventEntity> event() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference reference = storage.getReference("events.json");
        return RxFirebaseClass.getBytes(reference, MAX_SIZE_BUFFER)
                .observeOn(Schedulers.io())
                .toFlowable()
                .map(ByteArrayInputStream::new)
                .map(InputStreamReader::new)
                .map(inputStreamReader -> (List<EventEntity>)gson.fromJson(inputStreamReader, eventsListType))
                .flatMapIterable(list -> list)
                .observeOn(AndroidSchedulers.mainThread());
    }

}
