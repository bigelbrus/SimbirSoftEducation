package com.example.simbirsoftapp.data.db;

import com.example.simbirsoftapp.data.entity.CategoryEntity;

import io.reactivex.Flowable;

public class RoomImpl implements DbCategoryApi {
    @Override
    public Flowable<CategoryEntity> getCategory() {
        return null;
    }

    @Override
    public void put(CategoryEntity categoryEntity) {

    }

    @Override
    public boolean isSaved() {
        return false;
    }
}
