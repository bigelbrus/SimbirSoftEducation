package com.example.simbirsoftapp.di.component;


import com.example.simbirsoftapp.di.PerActivity;
import com.example.simbirsoftapp.di.PerFragment;
import com.example.simbirsoftapp.di.module.ActivityModule;
import com.example.simbirsoftapp.di.module.CategoryModule;
import com.example.simbirsoftapp.ui.help.HelpFragment;
import com.example.simbirsoftapp.ui.news.NewsFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = {ActivityModule.class, CategoryModule.class})
public interface CategoryComponent extends ActivityComponent {
    void inject(HelpFragment helpFragment);
}
