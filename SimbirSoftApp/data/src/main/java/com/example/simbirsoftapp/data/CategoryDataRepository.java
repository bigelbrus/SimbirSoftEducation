package com.example.simbirsoftapp.data;

import android.util.Log;

import com.example.simbirsoftapp.data.entity.mapper.CategoryEntityDataMapper;
import com.example.simbirsoftapp.data.repository.category.CategoryDataFactory;
import com.example.simbirsoftapp.data.repository.category.DatabaseCategoryDataStore;
import com.example.simbirsoftapp.data.repository.category.NetCategoryDataStore;
import com.example.simbirsoftapp.domain.Category;
import com.example.simbirsoftapp.domain.repository.CategoryRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Singleton
public class CategoryDataRepository implements CategoryRepository {
//    private final CategoryDataFactory categoryDataFactory;
    private DatabaseCategoryDataStore databaseCategoryDataStore;
    private NetCategoryDataStore netCategoryDataStore;
    private final CategoryEntityDataMapper categoryEntityDataMapper;

    @Inject
    CategoryDataRepository(
            DatabaseCategoryDataStore databaseCategoryDataStore, NetCategoryDataStore netCategoryDataStore,
//            CategoryDataFactory categoryDataFactory,
            CategoryEntityDataMapper categoryEntityDataMapper){
        this.categoryEntityDataMapper = categoryEntityDataMapper;
        this.databaseCategoryDataStore = databaseCategoryDataStore;
        this.netCategoryDataStore = netCategoryDataStore;
//        this.categoryDataFactory = categoryDataFactory;
    }
    @Override
    public Flowable<Category> category() {
        if (databaseCategoryDataStore.isExist()) {
            Log.d("tag","category exist");
            return databaseCategoryDataStore.category().map(categoryEntityDataMapper::transform);
        } else{
            Log.d("tag","category not exist");
            return netCategoryDataStore.category().map(categoryEntityDataMapper::transform);
        }
//        return categoryDataFactory.create().category().map(categoryEntityDataMapper::transform);
    }
}
