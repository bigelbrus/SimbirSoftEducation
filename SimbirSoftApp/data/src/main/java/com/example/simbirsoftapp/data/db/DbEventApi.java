package com.example.simbirsoftapp.data.db;

import com.example.simbirsoftapp.data.entity.EventEntity;

import io.reactivex.Flowable;
import io.reactivex.Single;

public interface DbEventApi {
    Flowable<EventEntity> getEvent();

    void put(EventEntity categoryEntity);

    boolean isEventExist();
}
