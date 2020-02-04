package com.example.simbirsoftapp.ui.news;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.simbirsoftapp.R;
import com.example.simbirsoftapp.data.DataSource;
import com.example.simbirsoftapp.data.model.Event;
import com.example.simbirsoftapp.ui.news.details.NewsDetailFragment;
import com.example.simbirsoftapp.utility.AppUtils;

import io.reactivex.disposables.Disposable;

public class NewsFragment extends Fragment implements NewsAdapter.NewsClickHolder {

    private RecyclerView newsRecyclerView;
    private ProgressBar newsProgressBar;
    private Disposable disposable;
    private NewsAdapter adapter;

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        newsRecyclerView = view.findViewById(R.id.news_recycler_view);
        newsProgressBar = view.findViewById(R.id.news_progress_bar);
        adapter = new NewsAdapter(getContext(), this);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        newsRecyclerView.setAdapter(adapter);
        showLoading();
        disposable = DataSource.getInstance().getEvents(getContext())
                .subscribe(event -> {
                    showResults();
                    adapter.addEvent(event);
                });
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            AppUtils.setActionBar(activity, view, R.string.news_label, false);
        }

        getActivity().findViewById(R.id.bottom_panel).setVisibility(View.VISIBLE);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_news, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onNewsClick(int position) {
        Event currentEvent = adapter.getEvent(position);
        getFragmentManager().beginTransaction().replace(R.id.fragment_main,
                NewsDetailFragment.newInstance(currentEvent))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    private void showLoading() {
        newsProgressBar.setVisibility(View.VISIBLE);
        newsRecyclerView.setVisibility(View.GONE);
    }

    private void showResults() {
        newsProgressBar.setVisibility(View.GONE);
        newsRecyclerView.setVisibility(View.VISIBLE);
    }
}
