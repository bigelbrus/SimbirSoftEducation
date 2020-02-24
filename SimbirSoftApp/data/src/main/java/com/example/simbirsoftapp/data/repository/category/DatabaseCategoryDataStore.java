package com.example.simbirsoftapp.data.repository.category;

import com.example.simbirsoftapp.data.db.DbCategoryApi;
import com.example.simbirsoftapp.data.entity.CategoryEntity;

import io.reactivex.Flowable;

public class DatabaseCategoryDataStore implements CategoryDataStore {
    private DbCategoryApi dbCategoryApi;

    DatabaseCategoryDataStore(DbCategoryApi dbCategoryApi) {
        this.dbCategoryApi = dbCategoryApi;
    }
    @Override
    public Flowable<CategoryEntity> category() {
        return dbCategoryApi.getCategory();
    }
}
