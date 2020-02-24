package com.example.simbirsoftapp.data.repository.category;

import com.example.simbirsoftapp.data.db.DbCategoryApi;
import com.example.simbirsoftapp.data.entity.CategoryEntity;
import com.example.simbirsoftapp.data.net.NetApi;
import com.example.simbirsoftapp.data.repository.category.CategoryDataStore;

import io.reactivex.Flowable;

public class NetCategoryDataStore implements CategoryDataStore {
    private NetApi netApi;
    private DbCategoryApi dbApi;

    NetCategoryDataStore(NetApi netApi,DbCategoryApi dbApi) {
        this.netApi = netApi;
        this.dbApi = dbApi;
    }
    @Override
    public Flowable<CategoryEntity> category() {
        return netApi.category().doOnNext(dbApi::put);
    }
}
