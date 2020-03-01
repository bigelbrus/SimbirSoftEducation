package com.example.simbirsoftapp.data.net;

import android.util.Log;

import com.example.simbirsoftapp.data.entity.CategoryEntity;
import com.example.simbirsoftapp.data.entity.EventEntity;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
@Singleton
public class FirebaseImpl implements NetApi {
    private static final long MAX_SIZE_BUFFER = 1024 * 1024L;
    private static final Type categoryListType = new TypeToken<List<CategoryEntity>>() {
    }.getType();
    private static final Type eventsListType = new TypeToken<List<EventEntity>>() {
    }.getType();
    private final Gson gson;

    @Inject
    public FirebaseImpl(Gson gson){
        this.gson = gson;
    }

    @Override
    public Flowable<CategoryEntity> category() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference reference = storage.getReference("categories.json");
        Log.d("tag","firebase get category");

        return RxFirebaseUtils.getBytes(reference, MAX_SIZE_BUFFER)
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
        return RxFirebaseUtils.getBytes(reference, MAX_SIZE_BUFFER)
                .observeOn(Schedulers.io())
                .toFlowable()
                .map(ByteArrayInputStream::new)
                .map(InputStreamReader::new)
                .map(inputStreamReader -> (List<EventEntity>)gson.fromJson(inputStreamReader, eventsListType))
                .flatMapIterable(list -> list)
                .observeOn(AndroidSchedulers.mainThread());
    }

}
