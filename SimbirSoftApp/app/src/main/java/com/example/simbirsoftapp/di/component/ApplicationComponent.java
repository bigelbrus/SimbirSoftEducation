package com.example.simbirsoftapp.di.component;

import android.content.Context;

import com.example.simbirsoftapp.MainActivity;
import com.example.simbirsoftapp.di.module.ApplicationModule;
import com.example.simbirsoftapp.domain.executor.PostExecutionThread;
import com.example.simbirsoftapp.domain.executor.ThreadExecutor;
import com.example.simbirsoftapp.domain.repository.CategoryRepository;
import com.example.simbirsoftapp.domain.repository.EventRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(MainActivity mainActivity);

    Context context();
    CategoryRepository categoryRepository();
    EventRepository eventRepository();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
}
