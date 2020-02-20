package com.example.simbirsoftapp.data;

import com.example.simbirsoftapp.data.entity.mapper.CategoryEntityDataMapper;
import com.example.simbirsoftapp.data.repository.CategoryDataFactory;
import com.example.simbirsoftapp.domain.Category;
import com.example.simbirsoftapp.domain.repository.CategoryRepository;

import javax.inject.Inject;
import javax.inject.Scope;
import javax.inject.Singleton;

import io.reactivex.Flowable;
@Singleton
public class CategoryDataRepository implements CategoryRepository {
    private final CategoryDataFactory categoryDataFactory;
    private final CategoryEntityDataMapper categoryEntityDataMapper;

    @Inject
    CategoryDataRepository(CategoryDataFactory categoryDataFactory, CategoryEntityDataMapper categoryEntityDataMapper){
        this.categoryEntityDataMapper = categoryEntityDataMapper;
        this.categoryDataFactory = categoryDataFactory;
    }
    @Override
    public Flowable<Category> category() {
        return categoryDataFactory.create().category().map(categoryEntityDataMapper::transform);
    }
}
