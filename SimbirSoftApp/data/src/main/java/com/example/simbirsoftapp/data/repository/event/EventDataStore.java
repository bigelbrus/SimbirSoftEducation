package com.example.simbirsoftapp.data.repository.event;

import com.example.simbirsoftapp.data.entity.EventEntity;

import io.reactivex.Flowable;
import io.reactivex.Single;

public interface EventDataStore {
    Flowable<EventEntity> event();
}
