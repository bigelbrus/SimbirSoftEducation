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
    public static final int POSITION_EVENT = 0;
    public static final int POSITION_ORGANISATION = 1;



    public SearchPagerAdapter(FragmentManager fm, Context context) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
    }

    @Override
    public int getCount() {
        return LIST_COUNT;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case POSITION_ORGANISATION:
                return SearchOrganizationListFragment.newInstance();
            case POSITION_EVENT:
            default:
                return SearchEventsListFragment.newInstance();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        Resources res = context.getResources();
        switch (position) {
            case POSITION_ORGANISATION:
                return res.getText(R.string.search_organisations);
            case POSITION_EVENT:
            default:
                return res.getText(R.string.search_events);
        }
    }
}
