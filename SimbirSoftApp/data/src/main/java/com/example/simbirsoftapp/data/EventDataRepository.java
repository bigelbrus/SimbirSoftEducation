package com.example.simbirsoftapp.data;

import com.example.simbirsoftapp.data.entity.mapper.EventEntityDataMapper;
import com.example.simbirsoftapp.data.repository.event.DatabaseEventDataStore;
import com.example.simbirsoftapp.data.repository.event.EventDataFactory;
import com.example.simbirsoftapp.data.repository.event.NetEventDataStore;
import com.example.simbirsoftapp.domain.Event;
import com.example.simbirsoftapp.domain.repository.EventRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Singleton
public class EventDataRepository implements EventRepository {

//    private EventDataFactory eventDataFactory;
    private EventEntityDataMapper eventEntityDataMapper;
    private DatabaseEventDataStore databaseEventDataStore;
    private NetEventDataStore netEventDataStore;

    @Inject
    EventDataRepository(DatabaseEventDataStore databaseEventDataStore,
                        NetEventDataStore netEventDataStore,
                        EventEntityDataMapper eventEntityDataMapper) {
//        this.eventDataFactory = eventDataFactory;
        this.eventEntityDataMapper = eventEntityDataMapper;
        this.netEventDataStore = netEventDataStore;
        this.databaseEventDataStore = databaseEventDataStore;
    }
    @Override
    public Flowable<Event> event() {
        if (databaseEventDataStore.isExist()) {
            return databaseEventDataStore.event().map(eventEntityDataMapper::transform);
        } else {
            return netEventDataStore.event().map(eventEntityDataMapper::transform);
        }
//        return eventDataFactory.create().event().map(eventEntityDataMapper::transform);
    }
}
