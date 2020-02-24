package com.example.simbirsoftapp.ui;

import moxy.MvpView;
import moxy.viewstate.strategy.alias.AddToEndSingle;

@AddToEndSingle
public interface BaseView extends MvpView {
    void showLoading();

    void showData();

    void showError();
}
