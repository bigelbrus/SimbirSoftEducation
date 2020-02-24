package com.example.simbirsoftapp.ui.search.events;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import com.example.simbirsoftapp.MainActivity;
import com.example.simbirsoftapp.R;
import com.example.simbirsoftapp.ui.search.Measured;
import com.example.simbirsoftapp.ui.search.Updatable;
import com.example.simbirsoftapp.utility.AppUtils;

import javax.inject.Inject;


public class SearchEventsListFragment extends ListFragment implements Measured, Updatable {
    public static final int LIST_SIZE = 5;
    @Inject
    AppUtils appUtils;

    public static SearchEventsListFragment newInstance(){
        return new SearchEventsListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ((MainActivity)getActivity()).getCategoryComponent().inject(this);
        ((ListView)view.findViewById(android.R.id.list)).setDivider(null);
        updateList(getContext());
        return view;
    }
    @Override
    public void updateList(Context context) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                R.layout.search_item, R.id.search_text_item,
                appUtils.getRandomStringArray(context, LIST_SIZE,R.array.events_list));
        setListAdapter(adapter);
    }

    @Override
    public int getListSize(){
        return LIST_SIZE;
    }

}
