package com.example.simbirsoftapp.ui.search;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.viewpager.widget.ViewPager;

import com.example.simbirsoftapp.MainActivity;
import com.example.simbirsoftapp.R;
import com.example.simbirsoftapp.databinding.FragmentSearchBinding;
import com.example.simbirsoftapp.ui.search.events.SearchEventsListFragment;
import com.example.simbirsoftapp.ui.search.organisations.SearchOrganizationListFragment;
import com.example.simbirsoftapp.utility.AppUtils;
import com.example.simbirsoftapp.view.SearchViewPager;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import static com.example.simbirsoftapp.ui.search.SearchPagerAdapter.POSITION_EVENT;
import static com.example.simbirsoftapp.ui.search.SearchPagerAdapter.POSITION_ORGANISATION;

public class SearchFragment extends Fragment {
    private EditText searchText;
    private TextView searchCountType;
    private SearchPagerAdapter adapter;
    private SearchViewPager searchViewPager;
    private static final int START_POSITION = 0;
    private int currentPosition = START_POSITION;
    private FragmentSearchBinding searchBinding;

    @Inject
    AppCompatActivity activity;
    @Inject
    AppUtils appUtils;
    @Inject
    Context context;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        searchBinding = FragmentSearchBinding.inflate(inflater,container,false);
        View view = searchBinding.getRoot();
        ((MainActivity)getActivity()).getCategoryComponent().inject(this);

        searchViewPager = searchBinding.searchViewPager;
        adapter = new SearchPagerAdapter(getChildFragmentManager(), getContext());
        searchViewPager.reMeasureCurrentPage(((Measured) adapter.getItem(0)).getListSize());
        searchViewPager.setAdapter(adapter);
        searchText = searchBinding.searchField.searchEditText;
        searchBinding.searchField.makeSearchButton.setOnClickListener(v->
            makeSearch(searchText.getText().toString())
        );
        searchCountType = searchBinding.searchResultType;
        setLabelAndHint(START_POSITION);

        searchBinding.searchTabLayout.setupWithViewPager(searchViewPager);
        searchViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                switch (position) {
                    case POSITION_ORGANISATION:
                        searchViewPager.reMeasureCurrentPage(SearchOrganizationListFragment.LIST_SIZE);
                        break;
                    case POSITION_EVENT:
                    default:
                        searchViewPager.reMeasureCurrentPage(SearchEventsListFragment.LIST_SIZE);
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {
                setLabelAndHint(position);
                currentPosition = position;
                switch (position) {
                    case POSITION_ORGANISATION:
                        ((ListFragment) getChildFragmentManager().getFragments().get(POSITION_EVENT)).setListAdapter(new ArrayAdapter<>(context,
                                R.layout.search_item, R.id.search_text_item,
                                appUtils.getRandomStringArray(context, SearchEventsListFragment.LIST_SIZE, R.array.events_list)));
                        break;
                    case POSITION_EVENT:
                    default:
                        ((ListFragment) getChildFragmentManager().getFragments().get(POSITION_ORGANISATION)).setListAdapter(new ArrayAdapter<>(context,
                                R.layout.search_item, R.id.search_text_item,
                                appUtils.getRandomStringArray(context, SearchOrganizationListFragment.LIST_SIZE, R.array.organisations_list)));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //don't need to implement
            }
        });

        ImageButton voiceButton = view.findViewById(R.id.voice_search_button);

        voiceButton.setOnClickListener(click ->
                Toast.makeText(context, "Speak!", Toast.LENGTH_SHORT).show());

        return view;
    }

    private void setLabelAndHint(int position) {
        int count = ((Measured) adapter.getItem(position)).getListSize();
        switch (position) {
            case 1:
                searchText.setHint(R.string.enter_organisation_name);
                searchCountType.setText(getResources().getQuantityString(R.plurals.organisation_plurals, count, count));
                break;
            case 0:
            default:
                searchText.setHint(R.string.enter_event_name);
                searchCountType.setText(getResources().getQuantityString(R.plurals.event_plurals, count, count));
                break;
        }
    }

    private void makeSearch(String query){
        if (query.length() == 0) {
            return;
        }
        String[] result = appUtils.findInArray(getContext(),query, currentPosition);
        ((ListFragment) getChildFragmentManager().getFragments().get(currentPosition)).setListAdapter(new ArrayAdapter<>(getContext(),
                R.layout.search_item, R.id.search_text_item,
                result));
        searchViewPager.reMeasureCurrentPage(result.length);
        if (currentPosition == POSITION_EVENT) {
            searchCountType.setText(getResources().getQuantityString(R.plurals.event_plurals, result.length, result.length));
        } else {
            searchCountType.setText(getResources().getQuantityString(R.plurals.organisation_plurals, result.length, result.length));
        }

    }

}
