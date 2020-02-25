package com.example.simbirsoftapp.di.module;

import android.content.Context;
import android.util.Log;

import com.example.simbirsoftapp.App;
import com.example.simbirsoftapp.UIThread;
import com.example.simbirsoftapp.data.CategoryDataRepository;
import com.example.simbirsoftapp.data.EventDataRepository;
import com.example.simbirsoftapp.data.db.DbCategoryApi;
import com.example.simbirsoftapp.data.db.DbEventApi;
import com.example.simbirsoftapp.data.db.RoomImpl;
import com.example.simbirsoftapp.data.entity.CategoryEntity;
import com.example.simbirsoftapp.data.entity.EventEntity;
import com.example.simbirsoftapp.data.entity.adapter.CategoryTypeAdapter;
import com.example.simbirsoftapp.data.entity.adapter.EventTypeAdapter;
import com.example.simbirsoftapp.data.executor.JobExecutor;
import com.example.simbirsoftapp.data.net.FirebaseImpl;
import com.example.simbirsoftapp.data.net.NetApi;
import com.example.simbirsoftapp.data.utils.DateUtils;
import com.example.simbirsoftapp.domain.executor.PostExecutionThread;
import com.example.simbirsoftapp.domain.executor.ThreadExecutor;
import com.example.simbirsoftapp.domain.repository.CategoryRepository;
import com.example.simbirsoftapp.domain.repository.EventRepository;
import com.example.simbirsoftapp.utility.AppUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private static final Type categoryListType = new TypeToken<List<CategoryEntity>>() {
    }.getType();
    private static final Type eventsListType = new TypeToken<List<EventEntity>>() {
    }.getType();
    private App app;

    public ApplicationModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Context applicationContext() {
        return app;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    CategoryRepository provideUserRepository(CategoryDataRepository categoryDataRepository) {
        return categoryDataRepository;
    }

    @Provides
    @Singleton
    EventRepository eventRepository(EventDataRepository eventDataRepository) {
        return eventDataRepository;
    }

    @Provides
    @Singleton
    DbCategoryApi dbCategoryApi(RoomImpl roomImpl) {
        return roomImpl;
    }

    @Provides
    @Singleton
    NetApi netApi(FirebaseImpl firebase) {
        return firebase;
    }

    @Provides
    @Singleton
    DbEventApi dbEventApi(RoomImpl roomImpl) {
        return roomImpl;
    }

    @Provides
    @Singleton
    Gson gson() {
        return new GsonBuilder()
                .registerTypeAdapter(categoryListType, new CategoryTypeAdapter())
                .registerTypeAdapter(eventsListType, new EventTypeAdapter())
                .create();
    }

    @Provides
    @Singleton
    DateUtils dateUtils() {
        return new DateUtils();
    }

    @Provides
    @Singleton
    FirebaseAuth firebaseAuth() {
        Log.d("tag","firebase auth");
        return FirebaseAuth.getInstance();
    }

    @Provides
    @Singleton
    AppUtils appUtils() {
        return new AppUtils();
    }
}
