package com.example.simbirsoftapp.ui.help;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.simbirsoftapp.MainActivity;
import com.example.simbirsoftapp.R;
import com.example.simbirsoftapp.databinding.FragmentHelpBinding;
import com.example.simbirsoftapp.presenter.CategoryPresenter;
import com.example.simbirsoftapp.data.model.CategoryModel;
import com.example.simbirsoftapp.ui.HelpView;
import com.example.simbirsoftapp.utility.AppUtils;

import javax.inject.Inject;
import javax.inject.Provider;

import io.reactivex.disposables.Disposable;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class HelpFragment extends MvpAppCompatFragment implements HelpView {

    private static final int RECYCLER_VIEW_COLUMNS_NUMBER = 2;

    private RecyclerView categoryRecyclerView;
    private ProgressBar categoryProgressBar;
    private TextView categoryError;
    private FragmentHelpBinding binding;
    @Inject
    CategoryAdapter adapter;
    @Inject
    AppCompatActivity activity;
    @Inject
    AppUtils appUtils;
    @Inject
    @InjectPresenter
    CategoryPresenter categoryPresenter;
    @ProvidePresenter
    CategoryPresenter provideCategoryPresenter() {
        return categoryPresenter;
    }

    public static HelpFragment newInstance() {
        return new HelpFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHelpBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        initViews();
        ((MainActivity)getActivity()).getCategoryComponent().inject(this);
        showLoading();
        setupRecyclerView();
        appUtils.setActionBar(activity, view, R.string.help_label, true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        categoryPresenter.setView(this);
        loadCategories();
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
        Log.d("tag","setupRecyclerView");
        categoryRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), RECYCLER_VIEW_COLUMNS_NUMBER));
        categoryRecyclerView.setAdapter(adapter);
        adapter.setItemClickListener(clickHolder);
    }

    private void loadCategories(){
        categoryPresenter.initialize();
    }

    private CategoryAdapter.CategoryClickHolder clickHolder = ()->
            ((MainActivity) getActivity()).onBottomButtonClick(getActivity().findViewById(R.id.button_news));

    private void initViews() {
        categoryRecyclerView = binding.rvHelp;
        categoryProgressBar = binding.categoryProgressBar;
        categoryError = binding.categoryError;
    }

}