package com.example.simbirsoftapp.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.simbirsoftapp.R;
import com.google.android.material.tabs.TabLayout;

public class SearchFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        MyViewPager searchViewPager = view.findViewById(R.id.search_view_pager);
        SearchPagerAdapter adapter = new SearchPagerAdapter(getChildFragmentManager(), getContext());
        searchViewPager.reMeasureCurrentPage(((Measured)adapter.getItem(0)).getListSize());
        searchViewPager.setAdapter(adapter);

        TabLayout searchTabLayout = view.findViewById(R.id.search_tab_layout);
        searchTabLayout.setupWithViewPager(searchViewPager);
        searchViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position) {
                    case 0:
                        searchViewPager.reMeasureCurrentPage(((Measured)adapter.getItem(0)).getListSize());
                        break;
                    case 1:
                        searchViewPager.reMeasureCurrentPage(((Measured)adapter.getItem(1)).getListSize());
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        ((SearchOrganizationListFragment) adapter.getItem(1)).updateList();
                        break;
                    case 1:
                        ((SearchEventsListFragment) adapter.getItem(0)).updateList();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        ImageButton findButton = view.findViewById(R.id.make_search_button);
        ImageButton voiceButton = view.findViewById(R.id.voice_search_button);

        findButton.setOnClickListener(click->{
            Toast.makeText(getContext(), "Make search!", Toast.LENGTH_SHORT).show();
        });
        voiceButton.setOnClickListener(click->{
            Toast.makeText(getContext(), "Speak!", Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}
