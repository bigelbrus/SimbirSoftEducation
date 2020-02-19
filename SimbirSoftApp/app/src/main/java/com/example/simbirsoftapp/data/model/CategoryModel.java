package com.example.simbirsoftapp.data.model;

import com.example.simbirsoftapp.data.database.RealmCategory;

public class CategoryModel {
    private int id;
    private String logo;
    private String text;

    public CategoryModel() {}


    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getLogo() {
        return logo;
    }
}
