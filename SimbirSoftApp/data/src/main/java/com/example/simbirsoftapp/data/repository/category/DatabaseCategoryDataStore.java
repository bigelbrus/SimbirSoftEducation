package com.example.simbirsoftapp.data.repository.category;

import com.example.simbirsoftapp.data.db.DbCategoryApi;
import com.example.simbirsoftapp.data.db.RoomImpl;
import com.example.simbirsoftapp.data.entity.CategoryEntity;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Singleton
public class DatabaseCategoryDataStore implements CategoryDataStore {
    private DbCategoryApi dbCategoryApi;

    @Inject
    DatabaseCategoryDataStore(DbCategoryApi dbCategoryApi) {
        this.dbCategoryApi = dbCategoryApi;
    }
    @Override
    public Flowable<CategoryEntity> category() {
        return dbCategoryApi.getCategory();
    }

    @Override
    public boolean isExist() {
        return dbCategoryApi.isCategoryExist();
    }
}
