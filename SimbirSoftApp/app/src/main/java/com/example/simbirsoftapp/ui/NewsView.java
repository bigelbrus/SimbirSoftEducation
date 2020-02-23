package com.example.simbirsoftapp.ui;

import com.example.simbirsoftapp.data.model.EventModel;

import moxy.viewstate.strategy.alias.AddToEndSingle;

@AddToEndSingle
public interface NewsView extends BaseView {

    void onEventClick();

    void showEvent(EventModel eventModel);
}
