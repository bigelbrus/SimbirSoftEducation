package com.example.simbirsoftapp.data.repository.event;

import com.example.simbirsoftapp.data.db.DbEventApi;
import com.example.simbirsoftapp.data.entity.EventEntity;

import io.reactivex.Flowable;

public class DatabaseEventDataStore implements EventDataStore {
    private DbEventApi dbEventApi;

    DatabaseEventDataStore(DbEventApi dbEventApi) {
        this.dbEventApi = dbEventApi;
    }
    @Override
    public Flowable<EventEntity> event() {
        return dbEventApi.getEvent();
    }
}
