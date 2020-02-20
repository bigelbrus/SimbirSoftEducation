package com.example.simbirsoftapp.di.module;

import android.content.Context;

import com.example.simbirsoftapp.App;
import com.example.simbirsoftapp.UIThread;
import com.example.simbirsoftapp.data.CategoryDataRepository;
import com.example.simbirsoftapp.data.executor.JobExecutor;
import com.example.simbirsoftapp.domain.executor.PostExecutionThread;
import com.example.simbirsoftapp.domain.executor.ThreadExecutor;
import com.example.simbirsoftapp.domain.repository.CategoryRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    App app;

    public ApplicationModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Context applicationContext(){
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
}
