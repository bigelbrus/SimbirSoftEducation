package com.example.simbirsoftapp.data.repository.category;

import android.content.Context;

import com.example.simbirsoftapp.data.db.DbCategoryApi;
import com.example.simbirsoftapp.data.net.NetApi;

import javax.inject.Inject;
import javax.inject.Singleton;

public class CategoryDataFactory {

    private Context context;
    private DbCategoryApi dbCategoryApi;
    private NetApi netApi;

    CategoryDataFactory(Context context,DbCategoryApi dbCategoryApi,NetApi netApi){
        this.context = context;
        this.dbCategoryApi = dbCategoryApi;
        this.netApi = netApi;
    }


//    public CategoryDataStore create() {
//        if (dbCategoryApi.isCategoryExist()) {
//            return new DatabaseCategoryDataStore(dbCategoryApi);
//        } else {
//            return new NetCategoryDataStore(netApi,dbCategoryApi);
//        }
//    }
}
