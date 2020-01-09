package com.example.simbirsoftapp.data.loader;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.simbirsoftapp.data.model.Response;

public abstract class BaseLoader extends AsyncTaskLoader<Response> {

    public BaseLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public Response loadInBackground() {
        Response response = call();
        if (response.getRequestResult()) {
            response.save(getContext());
            onSuccess();
        } else {
            onError();
        }
        return response;
    }

    protected void onSuccess(){}

    protected void onError(){}

    protected abstract Response call();

}
