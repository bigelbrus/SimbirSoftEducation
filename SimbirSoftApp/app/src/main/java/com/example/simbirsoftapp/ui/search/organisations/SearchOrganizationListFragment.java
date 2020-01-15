package com.example.simbirsoftapp.ui.search.organisations;

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

import com.example.simbirsoftapp.R;
import com.example.simbirsoftapp.ui.search.Measured;
import com.example.simbirsoftapp.ui.search.Updatable;
import com.example.simbirsoftapp.utility.AppUtils;


public class SearchOrganizationListFragment extends ListFragment implements Measured, Updatable {
    public static final int LIST_SIZE = 4;

    public static SearchOrganizationListFragment newInstance() {
        return new SearchOrganizationListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ((ListView)view.findViewById(android.R.id.list)).setDivider(null);
        updateList(getContext());
        return view;
    }

    public void updateList(Context context) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                R.layout.search_item, R.id.search_text_item,
                AppUtils.getRandomStringArray(context, LIST_SIZE,R.array.organisations_list));
        setListAdapter(adapter);
    }

    public int getListSize() {
        return LIST_SIZE;
    }
}
