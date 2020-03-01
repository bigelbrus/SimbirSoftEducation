package com.example.simbirsoftapp.data.repository.category;

import com.example.simbirsoftapp.data.entity.CategoryEntity;

import io.reactivex.Flowable;
import io.reactivex.Single;

public interface CategoryDataStore {

    Flowable<CategoryEntity> category();

    boolean isExist();
}
