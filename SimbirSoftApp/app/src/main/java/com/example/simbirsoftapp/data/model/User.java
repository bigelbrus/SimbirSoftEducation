package com.example.simbirsoftapp.data.model;

import com.example.simbirsoftapp.utility.DateUtils;

import java.util.Date;
import java.util.List;

public class User {
    private String name;
    private String surname;
    private Date date;
    private String activity;
    private boolean wantPush;
    private int logo;
    private String roundedLogo;
    private List<User> friends;

    public User(String name, String surname, int logo) {
        this.name = name;
        this.surname = surname;
        this.logo = logo;
    }

    public User(String name, String surname, String roundedLogo) {
        this.name = name;
        this.surname = surname;
        this.roundedLogo = roundedLogo;
    }

    public User(String name, String surname, String activity, boolean wantPush, int logo,
                List<User> friends, String date){
        this.name = name;
        this.surname = surname;
        this.activity = activity;
        this.wantPush = wantPush;
        this.logo = logo;
        this.friends = friends;
        this.date = DateUtils.parseDate(date);
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
        return DateUtils.formatDate(date);
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

    public void addFriend(User p) {
        friends.add(p);
    }

    public String getRoundedLogo() {
        return roundedLogo;
    }

    public void setRoundedLogo(String roundedLogo) {
        this.roundedLogo = roundedLogo;
    }

    public List<User> getFriends() {
        return friends;
    }

    public String getFullName() {
        return surname + " " + name;
    }
}