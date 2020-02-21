package com.example.simbirsoftapp.data.net;

import com.example.simbirsoftapp.data.entity.CategoryEntity;
import com.example.simbirsoftapp.data.entity.EventEntity;

import io.reactivex.Flowable;

public interface NetApi {

    Flowable<CategoryEntity> category();

    Flowable<EventEntity> event();
}
