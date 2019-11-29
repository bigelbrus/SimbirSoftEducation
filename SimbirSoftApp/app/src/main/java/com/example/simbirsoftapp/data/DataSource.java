package com.example.simbirsoftapp.data;

import com.example.simbirsoftapp.R;
import com.example.simbirsoftapp.data.model.Category;
import com.example.simbirsoftapp.data.model.User;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    private DataSource(){}

    public static Category[] getCategory() {
        return new Category[] {
                new Category(R.string.children, R.drawable.children),
                new Category(R.string.adult, R.drawable.adult),
                new Category(R.string.old, R.drawable.old),
                new Category(R.string.animal, R.drawable.animal),
                new Category(R.string.event, R.drawable.event),
        };
    }

    public static List<User> getFriends() {
        List<User> friends = new ArrayList<>();
        friends.add(new User("Дмитрий","Валерьевич",R.drawable.avatar_3));
        friends.add(new User("Евгений","Александров",R.drawable.avatar_2));
        friends.add(new User("Виктор","Кузнецов",R.drawable.avatar_1));
        friends.add(new User("Иван","Петров",R.drawable.avatar_3));
        return friends;
    }
}
