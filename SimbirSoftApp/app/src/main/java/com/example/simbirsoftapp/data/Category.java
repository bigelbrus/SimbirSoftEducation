package com.example.simbirsoftapp.data;

import com.example.simbirsoftapp.R;

public class Category {
    private int logo;
    private int text;

    private Category(int text, int logo) {
        this.logo = logo;
        this.text = text;

    }

    public static Category[] categories = {
            new Category(R.string.children, R.drawable.children),
            new Category(R.string.adult, R.drawable.adult),
            new Category(R.string.old, R.drawable.old),
            new Category(R.string.animal, R.drawable.animal),
            new Category(R.string.event, R.drawable.event),
    };

    public int getText() {
        return text;
    }

    public int getLogo() {
        return logo;
    }
}
