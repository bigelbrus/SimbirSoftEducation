package com.example.simbirsoftapp.data.model;

import com.example.simbirsoftapp.data.database.RealmUser;
import com.example.simbirsoftapp.utility.DateUtils;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneId;

import java.util.ArrayList;
import java.util.List;

public class User {
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

    public User(String name, String surname, int logo) {
        this.name = name;
        this.surname = surname;
        this.logo = logo;
    }

    public User(String name, String surname, String activity, boolean wantPush, int logo,
                List<User> friends, String date) {
        this.name = name;
        this.surname = surname;
        this.activity = activity;
        this.wantPush = wantPush;
        this.logo = logo;
        this.friends = friends;
        this.date = DateUtils.parseDate(date);
    }

    public User(RealmUser u) {
        name = u.getName();
        surname = u.getSurname();
        date = (u.getDate() != null) ? Instant.ofEpochMilli(u.getDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate() :
                null;
        activity = u.getActivity();
        wantPush = u.isWantPush();
        logo = u.getLogo();
        roundedLogo = u.getRoundedLogo();
        for (RealmUser f : u.getFriends()) {
            friends.add(new User(f));
        }
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

    public String getFullName() {
        return surname + " " + name;
    }
}
