package com.example.simbirsoftapp.data;

import com.example.simbirsoftapp.R;
import com.example.simbirsoftapp.data.model.UserModel;

import java.util.ArrayList;
import java.util.List;


public class DataForProfile {
    private static UserModel user;
    public static final String STANDARD_EVENT_IMAGE = "child";


    public static UserModel getUser() {
        if (user == null) {
            user = new UserModel("Денис", "Константинов", "Хирургия, травмвтология",
                    true, R.drawable.image_man,
                    DataForProfile.getFriends(), "01 02 1980");
        }
        return user;
    }

    private static List<UserModel> getFriends() {
        List<UserModel> friends = new ArrayList<>();
        friends.add(new UserModel("Дмитрий", "Валерьевич", R.drawable.avatar_3));
        friends.add(new UserModel("Евгений", "Александров", R.drawable.avatar_2));
        friends.add(new UserModel("Виктор", "Кузнецов", R.drawable.avatar_1));
        friends.add(new UserModel("Иван", "Петров", R.drawable.avatar_3));
        return friends;
    }

}




