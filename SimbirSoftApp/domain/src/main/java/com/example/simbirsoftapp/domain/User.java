package com.example.simbirsoftapp.domain;

import com.example.simbirsoftapp.domain.utils.DateUtils;

import org.threeten.bp.LocalDate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private int id;
    private String name;
    private String surname;
    private LocalDate date;
    private String activity;
    private boolean wantPush;
    private int logo;
    private String roundedLogo;
    private List<User> friends = new ArrayList<>();

    public User() {
    }

    public User(String name, String surname, int logo,String roundedLogo) {
        this.name = name;
        this.surname = surname;
        this.logo = logo;
        this.roundedLogo = roundedLogo;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public String getFullName() {
        return surname + " " + name;
    }
}
