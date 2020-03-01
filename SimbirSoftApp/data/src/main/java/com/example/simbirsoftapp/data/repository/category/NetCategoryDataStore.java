package com.example.simbirsoftapp.data.repository.category;

import android.util.Log;

import com.example.simbirsoftapp.data.db.DbCategoryApi;
import com.example.simbirsoftapp.data.entity.CategoryEntity;
import com.example.simbirsoftapp.data.net.NetApi;
import com.example.simbirsoftapp.data.repository.category.CategoryDataStore;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Singleton
public class NetCategoryDataStore implements CategoryDataStore {
    private NetApi netApi;
    private DbCategoryApi dbApi;

    @Inject
    NetCategoryDataStore(NetApi netApi,DbCategoryApi dbApi) {
        this.netApi = netApi;
        this.dbApi = dbApi;
    }
    @Override
    public Flowable<CategoryEntity> category() {
        Log.d("tag","netcategory datastore");

        return netApi.category().doOnNext(dbApi::put);
    }

    @Override
    public boolean isExist() {
        return false;
    }
}
