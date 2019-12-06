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

import com.example.simbirsoftapp.R;
import com.example.simbirsoftapp.data.DataSource;
import com.example.simbirsoftapp.data.model.Event;
import com.example.simbirsoftapp.ui.help.details.NewsDetailFragment;
import com.example.simbirsoftapp.utility.AppUtils;

import java.util.List;

public class NewsFragment extends Fragment implements NewsAdapter.NewsClickHolder {

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
        RecyclerView newsRecyclerView = view.findViewById(R.id.news_recycler_view);
        List<Event> events = DataSource.getEvents(getContext());
        NewsAdapter newsAdapter = new NewsAdapter(events,getContext(),this);
        newsRecyclerView.setAdapter(newsAdapter);
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
}
