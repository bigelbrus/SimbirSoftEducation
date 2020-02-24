package com.example.simbirsoftapp.data.repository.event;

import com.example.simbirsoftapp.data.db.DbEventApi;
import com.example.simbirsoftapp.data.entity.EventEntity;
import com.example.simbirsoftapp.data.net.NetApi;

import io.reactivex.Flowable;

public class NetEventDataStore implements EventDataStore {
    private NetApi netApi;
    private DbEventApi dbEventApi;

    NetEventDataStore(NetApi netApi, DbEventApi dbEventApi) {
        this.dbEventApi = dbEventApi;
        this.netApi = netApi;
    }
    @Override
    public Flowable<EventEntity> event() {
        return netApi.event().doOnNext(dbEventApi::put);
    }
}
