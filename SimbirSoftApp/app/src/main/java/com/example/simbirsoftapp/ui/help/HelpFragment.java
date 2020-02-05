package com.example.simbirsoftapp.ui.help;

import android.os.Bundle;

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
import com.example.simbirsoftapp.data.DataSource;
import com.example.simbirsoftapp.utility.AppUtils;

import java.util.ArrayList;

import io.reactivex.disposables.Disposable;

public class HelpFragment extends Fragment implements CategoryAdapter.CategoryClickHolder{

    private static final int RECYCLER_VIEW_COLUMNS_NUMBER = 2;

    private RecyclerView categoryRecyclerView;
    private ProgressBar categoryProgressBar;
    private TextView categoryError;
    private Disposable disposable;

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
        CategoryAdapter adapter = new CategoryAdapter(new ArrayList<>(), getContext(), this);
        categoryRecyclerView.setAdapter(adapter);
        showLoading();
        disposable = DataSource.getInstance().getCategories(getContext())
                .subscribe(category -> {
                    showData();
                    adapter.addCategory(category);
                });
        categoryRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), RECYCLER_VIEW_COLUMNS_NUMBER));
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            AppUtils.setActionBar(activity, view, R.string.help_label, true);
        }
        return view;
    }

    public void onCategoryClick() {
        ((MainActivity) getActivity()).onBottomButtonClick(getActivity().findViewById(R.id.button_news));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    private void showLoading() {
        categoryProgressBar.setVisibility(View.VISIBLE);
        categoryRecyclerView.setVisibility(View.GONE);
        categoryError.setVisibility(View.GONE);
    }

    private void showData() {
        categoryProgressBar.setVisibility(View.GONE);
        categoryRecyclerView.setVisibility(View.VISIBLE);
        categoryError.setVisibility(View.GONE);
    }

    private void showError() {
        categoryProgressBar.setVisibility(View.GONE);
        categoryRecyclerView.setVisibility(View.GONE);
        categoryError.setVisibility(View.VISIBLE);
    }

}
