package com.example.simbirsoftapp.data.repository.event;

import com.example.simbirsoftapp.data.db.DbEventApi;
import com.example.simbirsoftapp.data.entity.EventEntity;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Single;
@Singleton
public class DatabaseEventDataStore implements EventDataStore {
    private DbEventApi dbEventApi;

    @Inject
    DatabaseEventDataStore(DbEventApi dbEventApi) {
        this.dbEventApi = dbEventApi;
    }
    @Override
    public Flowable<EventEntity> event() {
        return dbEventApi.getEvent();
    }

    public boolean isExist(){
        return dbEventApi.isEventExist();
    }
}
