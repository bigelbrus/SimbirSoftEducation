package com.example.simbirsoftapp.domain.repository;

import com.example.simbirsoftapp.domain.Category;

import io.reactivex.Flowable;
import io.reactivex.Single;

public interface CategoryRepository {

    Flowable<Category> category();
}
