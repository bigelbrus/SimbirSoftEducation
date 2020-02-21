package com.example.simbirsoftapp.ui.help;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.simbirsoftapp.MainActivity;
import com.example.simbirsoftapp.R;
import com.example.simbirsoftapp.presenter.CategoryPresenter;
import com.example.simbirsoftapp.data.model.CategoryModel;
import com.example.simbirsoftapp.ui.HelpView;
import com.example.simbirsoftapp.utility.AppUtils;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class HelpFragment extends Fragment implements HelpView {

    private static final int RECYCLER_VIEW_COLUMNS_NUMBER = 2;

    private RecyclerView categoryRecyclerView;
    private ProgressBar categoryProgressBar;
    private TextView categoryError;
    @Inject
    CategoryAdapter adapter;
    @Inject
    CategoryPresenter categoryPresenter;

    public static HelpFragment newInstance() {
        return new HelpFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help, container, false);
        categoryRecyclerView = view.findViewById(R.id.rv_help);
        categoryProgressBar = view.findViewById(R.id.category_progress_bar);
        categoryError = view.findViewById(R.id.category_error);
        ((MainActivity)getActivity()).getCategoryComponent().inject(this);
        showLoading();
        setupRecyclerView();

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            AppUtils.setActionBar(activity, view, R.string.help_label, true);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        categoryPresenter.setView(this);
        if (savedInstanceState == null) {
            loadCategories();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        categoryPresenter.destroy();
    }
    @Override
    public void showLoading() {
        categoryProgressBar.setVisibility(View.VISIBLE);
        categoryRecyclerView.setVisibility(View.GONE);
        categoryError.setVisibility(View.GONE);
    }


    @Override
    public void showError() {
        categoryProgressBar.setVisibility(View.GONE);
        categoryRecyclerView.setVisibility(View.GONE);
        categoryError.setVisibility(View.VISIBLE);
    }

    @Override
    public Context context() {
        return this.getActivity().getApplicationContext();
    }
    @Override
    public void showData() {
        categoryProgressBar.setVisibility(View.GONE);
        categoryRecyclerView.setVisibility(View.VISIBLE);
        categoryError.setVisibility(View.GONE);
    }

    @Override
    public void showCategory(CategoryModel category) {
        adapter.addCategory(category);
    }

    private void setupRecyclerView(){
        categoryRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), RECYCLER_VIEW_COLUMNS_NUMBER));
        categoryRecyclerView.setAdapter(adapter);
        adapter.setItemClickListener(clickHolder);
    }

    private void loadCategories(){
        categoryPresenter.initialize();
    }

    private CategoryAdapter.CategoryClickHolder clickHolder = ()->
            ((MainActivity) getActivity()).onBottomButtonClick(getActivity().findViewById(R.id.button_news));

}
