package com.example.simbirsoftapp.ui.news;


import android.content.Context;
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

import com.example.simbirsoftapp.MainActivity;
import com.example.simbirsoftapp.R;
import com.example.simbirsoftapp.data.model.EventModel;
import com.example.simbirsoftapp.databinding.FragmentNewsBinding;
import com.example.simbirsoftapp.presenter.EventPresenter;
import com.example.simbirsoftapp.ui.NewsView;
import com.example.simbirsoftapp.ui.news.details.NewsDetailFragment;
import com.example.simbirsoftapp.utility.AppUtils;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class NewsFragment extends Fragment implements NewsAdapter.NewsClickHolder, NewsView {

    private RecyclerView newsRecyclerView;
    private ProgressBar newsProgressBar;
    private FragmentNewsBinding newsBinding;
    @Inject
    NewsAdapter adapter;
    @Inject
    AppCompatActivity activity;
    @Inject
    AppUtils appUtils;
    @Inject
    @InjectPresenter
    EventPresenter eventPresenter;

    @ProvidePresenter
    EventPresenter provideEventPresenter() {
        return eventPresenter;
    }

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
        newsBinding = FragmentNewsBinding.inflate(inflater,container,false);
        View view = newsBinding.getRoot();
        initViews();
        ((MainActivity)getActivity()).getCategoryComponent().inject(this);
        setupRecyclerView();
        showLoading();
        appUtils.setActionBar(activity, view, R.string.news_label, false);
        ((MainActivity)getActivity()).getBottomPanel().setVisibility(View.VISIBLE);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        eventPresenter.setView(this);
        if(savedInstanceState==null) {
            loadEvents();
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_news, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onNewsClick(int position) {
        EventModel currentEvent = adapter.getEvent(position);
        getFragmentManager().beginTransaction().replace(R.id.fragment_main,
                NewsDetailFragment.newInstance(currentEvent))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

      eventPresenter.destroy();
    }
    @Override
    public void showLoading() {
        newsProgressBar.setVisibility(View.VISIBLE);
        newsRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showData() {
        newsProgressBar.setVisibility(View.GONE);
        newsRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError() {

    }

    @Override
    public void onEventClick() {

    }

    @Override
    public void showEvent(EventModel eventModel) {
        adapter.addEvent(eventModel);
    }

    public Context context() {
        return getActivity().getApplicationContext();
    }

    private void showResults() {
        newsProgressBar.setVisibility(View.GONE);
        newsRecyclerView.setVisibility(View.VISIBLE);
    }

    private void setupRecyclerView() {
        newsRecyclerView.setAdapter(adapter);
        adapter.setUpClickHolder(newsClickHolder);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private NewsAdapter.NewsClickHolder newsClickHolder = (pos)->{
        EventModel currentEvent = adapter.getEvent(pos);
        getFragmentManager().beginTransaction().replace(R.id.fragment_main,
                NewsDetailFragment.newInstance(currentEvent))
                .addToBackStack(null)
                .commit();
    };

    private void loadEvents() {
        eventPresenter.initialize();
    }

    private void initViews() {
        newsRecyclerView = newsBinding.newsRecyclerView;
        newsProgressBar = newsBinding.newsProgressBar;
    }
}