package com.example.simbirsoftapp.domain.interactor;

import com.example.simbirsoftapp.domain.Event;
import com.example.simbirsoftapp.domain.executor.PostExecutionThread;
import com.example.simbirsoftapp.domain.executor.ThreadExecutor;
import com.example.simbirsoftapp.domain.repository.EventRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class GetEvent extends UseCase<Event,Void> {

    private EventRepository eventRepository;

    @Inject
    GetEvent(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
             EventRepository eventRepository) {
        super(threadExecutor,postExecutionThread);
        this.eventRepository = eventRepository;
    }

    @Override
    Flowable<Event> buildUseCaseObservable(Void aVoid) {
        return eventRepository.event();
    }
}
