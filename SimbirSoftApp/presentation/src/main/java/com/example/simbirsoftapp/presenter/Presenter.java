package com.example.simbirsoftapp.presenter;


import com.example.simbirsoftapp.ui.BaseView;

import moxy.MvpPresenter;

public abstract class Presenter<V extends BaseView> extends MvpPresenter<V> {
  abstract void resume();

  abstract void pause();

  abstract void destroy();
}
