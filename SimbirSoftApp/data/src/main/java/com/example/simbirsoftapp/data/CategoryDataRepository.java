package com.example.simbirsoftapp.data;

import com.example.simbirsoftapp.domain.Category;
import com.example.simbirsoftapp.domain.repository.CategoryRepository;

import io.reactivex.Flowable;

public class CategoryDataRepository implements CategoryRepository {
    @Override
    public Flowable<Category> category() {
        return null;
    }
}
