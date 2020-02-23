package com.example.simbirsoftapp.ui;

import com.example.simbirsoftapp.data.model.CategoryModel;

import moxy.viewstate.strategy.alias.AddToEnd;


@AddToEnd
public interface HelpView extends BaseView {
    void showCategory(CategoryModel category);
}
