package com.example.simbirsoftapp.data.repository.event;

import android.content.Context;

import com.example.simbirsoftapp.data.db.DbEventApi;
import com.example.simbirsoftapp.data.net.NetApi;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class EventDataFactory {
    private NetApi netApi;
    private DbEventApi dbEventApi;
    private Context context;

    @Inject
    EventDataFactory(Context context, NetApi netApi, DbEventApi dbEventApi){
        this.context = context;
        this.netApi = netApi;
        this.dbEventApi = dbEventApi;
    }

    public EventDataStore create() {
        if (dbEventApi.isEventExist()) {
            return new DatabaseEventDataStore(dbEventApi);
        } else {
            return new NetEventDataStore(netApi,dbEventApi);
        }
    }
}
