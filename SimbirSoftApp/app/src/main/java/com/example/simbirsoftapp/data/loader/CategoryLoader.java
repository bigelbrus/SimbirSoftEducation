package com.example.simbirsoftapp.data.loader;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.simbirsoftapp.data.DataSource;
import com.example.simbirsoftapp.data.model.Category;

import java.util.List;

public class CategoryLoader extends AsyncTaskLoader<List<Category>> {

    public CategoryLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public List<Category> loadInBackground() {
        return DataSource.getInstance().getCategories(getContext());
    }

}
