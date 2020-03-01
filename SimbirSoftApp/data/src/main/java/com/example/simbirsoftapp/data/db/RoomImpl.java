package com.example.simbirsoftapp.data.db;

import android.content.Context;

import com.example.simbirsoftapp.data.entity.CategoryEntity;
import com.example.simbirsoftapp.data.entity.EventEntity;
import com.example.simbirsoftapp.domain.executor.ThreadExecutor;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

@Singleton
public class RoomImpl implements DbCategoryApi,DbEventApi {
    private RoomDb mDb;
    private ThreadExecutor threadExecutor;

    @Inject
    public RoomImpl(Context context, ThreadExecutor threadExecutor) {
        this.mDb = RoomDb.getInstance(context.getApplicationContext());
        this.threadExecutor = threadExecutor;
    }

    @Override
    public Flowable<CategoryEntity> getCategory() {
        return mDb.categoryDao().getCategoryMaybe()
                .toFlowable()
                .flatMapIterable(list->list);
    }

    @Override
    public void put(CategoryEntity categoryEntity) {
        threadExecutor.execute(() -> {
            mDb.categoryDao().insertCategory(categoryEntity);
        });
    }

    @Override
    public Flowable<EventEntity> getEvent() {
        return mDb.categoryDao().getEventMaybe()
                .toFlowable()
                .flatMapIterable(list->list);
    }

    @Override
    public void put(EventEntity eventEntity) {
        threadExecutor.execute(()-> mDb.categoryDao().insertEvent(eventEntity));
    }

    @Override
    public boolean isCategoryExist() {
        return !mDb.categoryDao().getCategoryList().isEmpty();
    }

    @Override
    public boolean isEventExist() {
        return !mDb.categoryDao().getEventList().isEmpty();
    }
}
