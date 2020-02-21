package com.example.simbirsoftapp.data;

import com.example.simbirsoftapp.data.entity.mapper.EventEntityDataMapper;
import com.example.simbirsoftapp.data.repository.EventDataFactory;
import com.example.simbirsoftapp.domain.Event;
import com.example.simbirsoftapp.domain.repository.EventRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

@Singleton
public class EventDataRepository implements EventRepository {

    private EventDataFactory eventDataFactory;
    private EventEntityDataMapper eventEntityDataMapper;

    @Inject
    EventDataRepository(EventDataFactory eventDataFactory,EventEntityDataMapper eventEntityDataMapper) {
        this.eventDataFactory = eventDataFactory;
        this.eventEntityDataMapper = eventEntityDataMapper;
    }
    @Override
    public Flowable<Event> event() {
        return eventDataFactory.create().event().map(eventEntityDataMapper::transform);
    }
}
