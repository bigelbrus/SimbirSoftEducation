package com.example.simbirsoftapp.data.loader;

import android.content.Context;

import com.example.simbirsoftapp.data.DataSource;
import com.example.simbirsoftapp.data.model.Response;

public class CategoryLoader extends BaseLoader {

    public CategoryLoader(Context context) {
        super(context);
    }

    @Override
    protected Response call() {
        return DataSource.getCategories(getContext());

    }
}
