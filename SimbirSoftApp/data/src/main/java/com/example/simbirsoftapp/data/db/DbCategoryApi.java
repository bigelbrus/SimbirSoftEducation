package com.example.simbirsoftapp.data.db;

import com.example.simbirsoftapp.data.entity.CategoryEntity;

import io.reactivex.Flowable;
import io.reactivex.Single;

public interface DbCategoryApi {
    Flowable<CategoryEntity> getCategory();

    void put(CategoryEntity categoryEntity);

    boolean isCategoryExist();
}
