package com.example.simbirsoftapp.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.simbirsoftapp.R;
import com.example.simbirsoftapp.ui.search.events.SearchEventsListFragment;
import com.example.simbirsoftapp.ui.search.organisations.SearchOrganizationListFragment;
import com.example.simbirsoftapp.view.SearchViewPager;
import com.google.android.material.tabs.TabLayout;

public class SearchFragment extends Fragment {
    private EditText searchHint;
    private TextView searchCountType;
    private SearchPagerAdapter adapter;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        SearchViewPager searchViewPager = view.findViewById(R.id.search_view_pager);
        adapter = new SearchPagerAdapter(getChildFragmentManager(), getContext());
        searchViewPager.reMeasureCurrentPage(((Measured) adapter.getItem(0)).getListSize());
        searchViewPager.setAdapter(adapter);
        searchHint = view.findViewById(R.id.textInputEditText);
        searchCountType = view.findViewById(R.id.search_result_type);
        setLabelAndHint(0);

        TabLayout searchTabLayout = view.findViewById(R.id.search_tab_layout);
        searchTabLayout.setupWithViewPager(searchViewPager);
        searchViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position) {
                    case 1:
                        searchViewPager.reMeasureCurrentPage(((Measured) adapter.getItem(1)).getListSize());
                        break;
                    case 0:
                    default:
                        searchViewPager.reMeasureCurrentPage(((Measured) adapter.getItem(0)).getListSize());
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {
                setLabelAndHint(position);
                switch (position) {
                    case 1:
                        ((SearchEventsListFragment) adapter.getItem(0)).updateList();
                        break;
                    case 0:
                    default:
                        ((SearchOrganizationListFragment) adapter.getItem(1)).updateList();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //don't need to implement
            }
        });

        ImageButton findButton = view.findViewById(R.id.make_search_button);
        ImageButton voiceButton = view.findViewById(R.id.voice_search_button);

        findButton.setOnClickListener(click ->
            Toast.makeText(getContext(), "Make search!", Toast.LENGTH_SHORT).show());

        voiceButton.setOnClickListener(click ->
            Toast.makeText(getContext(), "Speak!", Toast.LENGTH_SHORT).show());

        return view;
    }

    private void setLabelAndHint(int position) {
        int count = ((Measured) adapter.getItem(position)).getListSize();
        switch (position) {
            case 1:
                searchHint.setHint(R.string.enter_organisation_name);
                searchCountType.setText(getResources().getQuantityString(R.plurals.organisation_plurals, count, count));
                break;
            case 0:
            default:
                searchHint.setHint(R.string.enter_event_name);
                searchCountType.setText(getResources().getQuantityString(R.plurals.event_plurals, count, count));
                break;
        }
    }
}
