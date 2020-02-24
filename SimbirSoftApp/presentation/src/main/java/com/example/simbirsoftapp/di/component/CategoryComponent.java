package com.example.simbirsoftapp.di.component;


import com.example.simbirsoftapp.di.PerActivity;
import com.example.simbirsoftapp.di.module.ActivityModule;
import com.example.simbirsoftapp.di.module.CategoryModule;
import com.example.simbirsoftapp.ui.auth.AuthFragment;
import com.example.simbirsoftapp.ui.help.HelpFragment;
import com.example.simbirsoftapp.ui.news.NewsFragment;
import com.example.simbirsoftapp.ui.news.details.NewsDetailFragment;
import com.example.simbirsoftapp.ui.profile.ProfileFragment;
import com.example.simbirsoftapp.ui.search.SearchFragment;
import com.example.simbirsoftapp.ui.search.events.SearchEventsListFragment;
import com.example.simbirsoftapp.ui.search.organisations.SearchOrganizationListFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = {ActivityModule.class, CategoryModule.class})
public interface CategoryComponent extends ActivityComponent {
    void inject(HelpFragment helpFragment);
    void inject(NewsFragment newsFragment);
    void inject(NewsDetailFragment newsDetailFragment);
    void inject(AuthFragment authFragment);
    void inject(ProfileFragment profileFragment);
    void inject(SearchFragment searchFragment);
    void inject(SearchEventsListFragment searchEventsListFragment);
    void inject(SearchOrganizationListFragment searchOrganizationListFragment);
}
