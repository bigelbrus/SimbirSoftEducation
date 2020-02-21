package com.example.simbirsoftapp.data.model;

import com.example.simbirsoftapp.utility.DateUtils;

import org.threeten.bp.LocalDate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserModel implements Serializable {
    private String name;
    private String surname;
    private LocalDate date;
    private String activity;
    private boolean wantPush;
    private int logo;
    private String roundedLogo;
    private List<UserModel> friends = new ArrayList<>();

    public UserModel() {
    }

    public UserModel(String name, String surname, int logo,String roundedLogo) {
        this.name = name;
        this.surname = surname;
        this.logo = logo;
        this.roundedLogo = roundedLogo;
    }

    public UserModel(String name, String surname, int logo) {
        this.name = name;
        this.surname = surname;
        this.logo = logo;
    }

    public UserModel(String name, String surname, String activity, boolean wantPush, int logo,
                List<UserModel> friends, String date) {
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

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStringDate() {
        return DateUtils.formatDate(date);
    }

    public LocalDate getDate() {
        return date;
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

    public void addFriend(UserModel p) {
        friends.add(p);
    }

    public String getRoundedLogo() {
        return this.roundedLogo;
    }

    public void setRoundedLogo(String roundedLogo) {
        this.roundedLogo = roundedLogo;
    }

    public List<UserModel> getFriends() {
        return friends;
    }

    public String getFullName() {
        return surname + " " + name;
    }
}
