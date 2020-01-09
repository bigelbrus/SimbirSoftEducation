package com.example.simbirsoftapp.data.model;

import com.example.simbirsoftapp.data.database.RealmCategory;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Category extends RealmObject {
    @PrimaryKey
    private int id;
    private String logo;
    private String text;

    public Category() {}

    public Category(String text, String logo) {
        this.logo = logo;
        this.text = text;
    }

    public Category(RealmCategory c) {
        logo = c.getLogo();
        text = c.getText();
        id = c.getId();
    }

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
