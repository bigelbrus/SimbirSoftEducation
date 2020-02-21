package com.example.simbirsoftapp.ui;

import android.content.Context;

import com.example.simbirsoftapp.data.model.CategoryModel;


public interface HelpView {

    void showLoading();

    void showData();

    void showError();

    void showCategory(CategoryModel category);

    Context context();
}
