package com.example.simbirsoftapp.data.repository;

import com.example.simbirsoftapp.data.db.RoomImpl;
import com.example.simbirsoftapp.data.net.FirebaseImpl;

public class CategoryDataFactory {

    CategoryDataFactory(){}


    public CategoryDataStore create() {
        return new NetCategoryDataStore(new FirebaseImpl(),new RoomImpl());
    }
}
