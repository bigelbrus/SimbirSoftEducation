package com.example.simbirsoftapp.data.net;

import com.example.simbirsoftapp.data.entity.CategoryEntity;

import io.reactivex.Flowable;

public interface NetApi {

    Flowable<CategoryEntity> category();
}
