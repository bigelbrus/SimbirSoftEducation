package com.example.simbirsoftapp.ui.search;

import android.content.Context;
import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.simbirsoftapp.R;
import com.example.simbirsoftapp.ui.search.events.SearchEventsListFragment;
import com.example.simbirsoftapp.ui.search.organisations.SearchOrganizationListFragment;

public class SearchPagerAdapter extends FragmentPagerAdapter {
    private static final int LIST_COUNT = 2;
    private Context context;
    private SearchOrganizationListFragment organizationListFragment;
    private SearchEventsListFragment eventsListFragment;


    public SearchPagerAdapter(FragmentManager fm, Context context) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
        organizationListFragment = new SearchOrganizationListFragment();
        eventsListFragment = new SearchEventsListFragment();
    }

    @Override
    public int getCount() {
        return LIST_COUNT;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return organizationListFragment;
            case 0:
            default:
                return eventsListFragment;
        }
    }

    public void setEventsListFragment() {
        eventsListFragment = new SearchEventsListFragment();
    }

    public void setOrganizationListFragment() {
        organizationListFragment = new SearchOrganizationListFragment();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        Resources res = context.getResources();
        switch (position) {
            case 1:
                return res.getText(R.string.search_organisations);
            case 0:
            default:
                return res.getText(R.string.search_events);
        }
    }
}
