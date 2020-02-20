package com.example.simbirsoftapp.data.repository;

import com.example.simbirsoftapp.data.db.RoomImpl;
import com.example.simbirsoftapp.data.net.FirebaseImpl;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CategoryDataFactory {

    @Inject
    CategoryDataFactory(){}


    public CategoryDataStore create() {
        return new NetCategoryDataStore(new FirebaseImpl(),new RoomImpl());
    }
}
