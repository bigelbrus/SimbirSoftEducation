package com.example.simbirsoftapp.ui.news;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.simbirsoftapp.R;
import com.example.simbirsoftapp.data.loader.EventsLoader;
import com.example.simbirsoftapp.data.model.Event;
import com.example.simbirsoftapp.ui.help.details.NewsDetailFragment;
import com.example.simbirsoftapp.utility.AppUtils;

import java.util.List;

public class NewsFragment extends Fragment implements NewsAdapter.NewsClickHolder,
        LoaderManager.LoaderCallbacks<List<Event>> {

    private RecyclerView newsRecyclerView;
    private ProgressBar newsProgressBar;

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
        View view = inflater.inflate(R.layout.fragment_news,container,false);
        newsRecyclerView = view.findViewById(R.id.news_recycler_view);
        newsProgressBar = view.findViewById(R.id.news_progress_bar);
        LoaderManager.getInstance(this).initLoader(R.id.news_recycler_view,Bundle.EMPTY,this);
        showLoading();
        Log.d("tag","onCreateView news");
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            AppUtils.setActionBar(activity, view, R.string.news_label, false);
        }

        getActivity().findViewById(R.id.bottom_panel).setVisibility(View.VISIBLE);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_news,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onNewsClick(int position) {
        getFragmentManager().beginTransaction().replace(R.id.fragment_main,
                NewsDetailFragment.newInstance(position))
                .addToBackStack(null)
                .commit();
    }

    @NonNull
    @Override
    public Loader<List<Event>> onCreateLoader(int id, @Nullable Bundle args) {
        return new EventsLoader(getContext());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Event>> loader, List<Event> data) {
        NewsAdapter newsAdapter = new NewsAdapter(data,getContext(),this);
        newsRecyclerView.setAdapter(newsAdapter);
        showResults();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Event>> loader) {
        //will be implemented soon
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
