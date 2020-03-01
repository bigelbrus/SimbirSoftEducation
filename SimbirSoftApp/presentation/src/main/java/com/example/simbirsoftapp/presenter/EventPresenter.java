package com.example.simbirsoftapp.presenter;

import com.example.simbirsoftapp.data.mapper.EventModelDataMapper;
import com.example.simbirsoftapp.data.model.EventModel;
import com.example.simbirsoftapp.domain.Event;
import com.example.simbirsoftapp.domain.interactor.GetEvent;
import com.example.simbirsoftapp.ui.NewsView;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.observers.ResourceSingleObserver;
import io.reactivex.subscribers.ResourceSubscriber;
import moxy.InjectViewState;

@InjectViewState
public class EventPresenter extends Presenter<NewsView> {

    private NewsView newsView;

    private GetEvent getEvent;
    private EventModelDataMapper eventModelDataMapper;

    @Inject
    EventPresenter(GetEvent getEvent, EventModelDataMapper eventModelDataMapper) {
        this.getEvent = getEvent;
        this.eventModelDataMapper = eventModelDataMapper;
    }

    public void setView(NewsView newsView) {
        this.newsView = newsView;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        getEvent.dispose();
        newsView = null;
    }

    public void initialize(){
        loadEvent();
        getEvents();
    }

    private void loadEvent(){
        showViewLoading();
    }

    private void showViewData(){
        newsView.showData();
    }

    private void showViewLoading(){
        newsView.showLoading();
    }

    private void showViewError() {
        newsView.showError();
    }

    private void showUsersCollectionInView(Event event) {
        EventModel eventModel= eventModelDataMapper.transform(event);
        this.newsView.showEvent(eventModel);
    }

    private void getEvents(){
        getEvent.execute(new EventsFlowable(),null);
    }

    private final class EventsFlowable extends ResourceSubscriber<Event> {

        @Override public void onComplete() {
            showViewData();
        }

        @Override
        public void onNext(Event event) {
            showUsersCollectionInView(event);
        }
//
//        @Override
//        public void onSuccess(Event event) {
//            showViewData();
//            showUsersCollectionInView(event);
//        }

        @Override public void onError(Throwable e) {
            showViewError();
        }
    }
}
