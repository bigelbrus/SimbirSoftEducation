package com.example.simbirsoftapp.data.repository.event;

import com.example.simbirsoftapp.data.db.DbEventApi;
import com.example.simbirsoftapp.data.entity.EventEntity;
import com.example.simbirsoftapp.data.net.NetApi;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Single;
@Singleton
public class NetEventDataStore implements EventDataStore {
    private NetApi netApi;
    private DbEventApi dbEventApi;
    @Inject
    NetEventDataStore(NetApi netApi, DbEventApi dbEventApi) {
        this.dbEventApi = dbEventApi;
        this.netApi = netApi;
    }
    @Override
    public Flowable<EventEntity> event() {
        return netApi.event().doOnNext(dbEventApi::put);
    }
}
