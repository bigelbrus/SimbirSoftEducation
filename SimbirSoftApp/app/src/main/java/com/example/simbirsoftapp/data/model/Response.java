package com.example.simbirsoftapp.data.model;

import android.content.Context;

public class Response {
    private Object answer;
    private boolean goodRequest;

    public Response() {
        goodRequest = false;
    }

    public Response setRequestResult(boolean result) {
        goodRequest = result;
        return this;
    }

    public boolean getRequestResult() {
        return goodRequest;
    }

    public <T> T getTypedAnswer() {
        return (answer == null) ? null : (T) answer;
    }

    public Response setAnswer(Object answer) {
        this.answer = answer;
        return this;
    }

    public void save(Context context){}
}
