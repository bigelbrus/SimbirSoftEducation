package com.example.simbirsoftapp.data.db;

import android.content.Context;
import android.util.Log;

import com.example.simbirsoftapp.data.entity.CategoryEntity;
import com.example.simbirsoftapp.domain.executor.ThreadExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class RoomImpl implements DbCategoryApi {
    private RoomDb mDb;
    private ThreadExecutor threadExecutor;

    @Inject
    public RoomImpl(Context context, ThreadExecutor threadExecutor) {
        this.mDb = RoomDb.getInstance(context.getApplicationContext());
        this.threadExecutor = threadExecutor;
    }

    @Override
    public Flowable<CategoryEntity> getCategory() {
        return Flowable.just(mDb.categoryDao().getCategories())
                .flatMapIterable(list -> list);
    }

    @Override
    public void put(CategoryEntity categoryEntity) {
        threadExecutor.execute(() -> {
            mDb.categoryDao().insertCategory(categoryEntity);
        });
    }

    @Override
    public boolean isExist() {
        return mDb.categoryDao().getCategories() != null;
    }
}
