package com.example.simbirsoftapp.data.repository;

import com.example.simbirsoftapp.data.entity.CategoryEntity;

import io.reactivex.Flowable;

public interface CategoryDataStore {

    Flowable<CategoryEntity> category();
}
