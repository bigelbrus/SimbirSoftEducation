package com.example.simbirsoftapp.data.database;

import com.example.simbirsoftapp.data.model.Category;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmCategory extends RealmObject {
    @PrimaryKey
    private int id;
    private String logo;
    private String text;

    public RealmCategory(Category c) {
        id = c.getId();
        logo = c.getLogo();
        text = c.getText();
    }

    public RealmCategory() {}

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
