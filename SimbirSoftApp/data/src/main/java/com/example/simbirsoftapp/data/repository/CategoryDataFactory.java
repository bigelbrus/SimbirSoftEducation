package com.example.simbirsoftapp.data.repository;

import android.content.Context;

import com.example.simbirsoftapp.data.db.DbCategoryApi;
import com.example.simbirsoftapp.data.db.RoomImpl;
import com.example.simbirsoftapp.data.net.FirebaseImpl;
import com.example.simbirsoftapp.data.net.NetApi;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CategoryDataFactory {

    Context context;
    DbCategoryApi dbCategoryApi;
    NetApi netApi;

    @Inject
    CategoryDataFactory(Context context,DbCategoryApi dbCategoryApi,NetApi netApi){
        this.context = context;
        this.dbCategoryApi = dbCategoryApi;
        this.netApi = netApi;
    }


    public CategoryDataStore create() {
        if (dbCategoryApi.isExist()) {
            return new DatabaseCategoryDataStore(dbCategoryApi);
        } else {
            return new NetCategoryDataStore(netApi,dbCategoryApi);
        }
    }
}
