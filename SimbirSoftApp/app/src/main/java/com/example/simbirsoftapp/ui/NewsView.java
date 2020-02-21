package com.example.simbirsoftapp.ui;

import android.content.Context;

import com.example.simbirsoftapp.data.model.EventModel;

public interface NewsView {
    void showLoading();

    void showData();

    void showError();

    void onEventClick();

    void showEvent(EventModel eventModel);

    Context context();
}
