package com.example.simbirsoftapp.ui.help;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.simbirsoftapp.MainActivity;
import com.example.simbirsoftapp.R;
import com.example.simbirsoftapp.data.loader.CategoryLoader;
import com.example.simbirsoftapp.data.model.Category;
import com.example.simbirsoftapp.data.model.Response;
import com.example.simbirsoftapp.utility.AppUtils;

import java.util.List;

public class HelpFragment extends Fragment implements CategoryAdapter.CategoryClickHolder,
        LoaderManager.LoaderCallbacks<List<Category>> {

    private static final int RECYCLER_VIEW_COLUMNS_NUMBER = 2;

    private RecyclerView categoryRecyclerView;
    private ProgressBar categoryProgressBar;

    public static HelpFragment newInstance() {
        return new HelpFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help, container, false);
        categoryRecyclerView = view.findViewById(R.id.rv_help);
        categoryProgressBar = view.findViewById(R.id.category_progress_bar);
        showLoading();
        LoaderManager.getInstance(this).initLoader(R.id.rv_help,Bundle.EMPTY,this);

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


    @NonNull
    @Override
    public Loader<List<Category>> onCreateLoader(int id, @Nullable Bundle args) {
        return new CategoryLoader(getContext());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Category>> loader, List<Category> data) {
        categoryRecyclerView.setAdapter(new CategoryAdapter(data, getContext(),this));
        showData();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Category>> loader) {

    }

    private void showLoading() {
        categoryProgressBar.setVisibility(View.VISIBLE);
        categoryRecyclerView.setVisibility(View.GONE);
    }

    private void showData() {
        categoryProgressBar.setVisibility(View.GONE);
        categoryRecyclerView.setVisibility(View.VISIBLE);
    }

}
