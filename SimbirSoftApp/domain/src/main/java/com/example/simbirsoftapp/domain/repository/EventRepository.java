package com.example.simbirsoftapp.domain.repository;

import com.example.simbirsoftapp.domain.Event;

import io.reactivex.Flowable;
import io.reactivex.Single;

public interface EventRepository {

    Flowable<Event> event();
}
