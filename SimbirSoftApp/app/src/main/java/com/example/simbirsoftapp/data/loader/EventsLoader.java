package com.example.simbirsoftapp.data.loader;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.simbirsoftapp.data.DataSource;
import com.example.simbirsoftapp.data.model.Event;

import java.util.List;

public class EventsLoader extends AsyncTaskLoader<List<Event>> {

    public EventsLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public List<Event> loadInBackground() {
        return DataSource.getInstance().getEvents(getContext());
    }

}
