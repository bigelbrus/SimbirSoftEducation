package com.example.simbirsoftapp.ui.help;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.simbirsoftapp.MainActivity;
import com.example.simbirsoftapp.R;
import com.example.simbirsoftapp.data.DataSource;
import com.example.simbirsoftapp.utility.AppUtils;

public class HelpFragment extends Fragment implements CategoryAdapter.CategoryClickHolder {

    private static final int RECYCLER_VIEW_COLUMNS_NUMBER = 2;

    public static HelpFragment newInstance() {
        return new HelpFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help, container, false);
        RecyclerView categoryRecyclerView = view.findViewById(R.id.rv_help);
        categoryRecyclerView.setAdapter(new CategoryAdapter(DataSource.getCategories(getContext()), getContext(),this));

        categoryRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), RECYCLER_VIEW_COLUMNS_NUMBER));


        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            AppUtils.setActionBar(activity, view, R.string.help_label, true);
        }
        return view;
    }

    public void onCategoryClick() {
        ((MainActivity)getActivity()).onBottomButtonClick(getActivity().findViewById(R.id.button_news));
    }



}
