package com.example.simbirsoftapp.data.model;

public class Category {
    private int logo;
    private int text;

    public Category(int text, int logo) {
        this.logo = logo;
        this.text = text;
    }

    public int getText() {
        return text;
    }

    public int getLogo() {
        return logo;
    }
}
