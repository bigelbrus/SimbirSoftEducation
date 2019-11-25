package com.example.simbirsoftapp.data;

import com.example.simbirsoftapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class User {
    private String name;
    private String surname;
    private Date date;
    private String activity;
    private boolean wantPush;
    private int logo;
    private ArrayList<Person> friends;
    private static User user;
    SimpleDateFormat dateFormat;

    public static User getUser() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    private User() {
        name = "Денис";
        surname = "Константинов";
        date = new Date();
        activity = "Хирургия, травмвтология";
        wantPush = true;
        logo = R.drawable.image_man;
        friends = new ArrayList<>();
        dateFormat = new SimpleDateFormat("dd MMMM YYYY", Locale.getDefault());
        long ls = 0;
        try {
            ls = dateFormat.parse("01-02-1980").getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        date = new Date(ls);
        friends.add(new Person("Дмитрий","Валерьевич",R.drawable.avatar_3));
        friends.add(new Person("Евгений","Александров",R.drawable.avatar_2));
        friends.add(new Person("Виктор","Кузнецов",R.drawable.avatar_1));
        friends.add(new Person("Иван","Петров",R.drawable.avatar_3));
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDate() {
        return dateFormat.format(date);
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getActivity() {
        return activity;
    }

    public void setWantPush(boolean wantPush) {
        this.wantPush = wantPush;
    }

    public boolean isWantPush() {
        return wantPush;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public int getLogo() {
        return logo;
    }

    public void addFriend(Person p) {
        friends.add(p);
    }

    public ArrayList<Person> getFriends() {
        return friends;
    }

    public String getFullName() {
        return surname + " " + name;
    }
}
