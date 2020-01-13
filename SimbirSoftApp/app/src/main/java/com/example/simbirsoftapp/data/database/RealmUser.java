package com.example.simbirsoftapp.data.database;

import com.example.simbirsoftapp.data.model.User;

import org.threeten.bp.DateTimeUtils;
import org.threeten.bp.ZoneId;

import java.util.Date;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class RealmUser extends RealmObject {
    private String name;
    private String surname;
    private Date date;
    private String activity;
    private boolean wantPush;
    private int logo;
    private String roundedLogo;
    private RealmList<RealmUser> friends = new RealmList<>();

    public RealmUser() {
    }

    public RealmUser(User u) {
        name = u.getName();
        surname = u.getSurname();
        date = (u.getDate() != null) ? DateTimeUtils.toDate(u.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant())
                : null;
        activity = u.getActivity();
        wantPush = u.isWantPush();
        logo = u.getLogo();
        roundedLogo = u.getRoundedLogo();
        for (User friend : u.getFriends()) {
            friends.add(new RealmUser(friend));
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

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
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

    public void addFriend(RealmUser p) {
        friends.add(p);
    }

    public String getRoundedLogo() {
        return roundedLogo;
    }

    public void setRoundedLogo(String roundedLogo) {
        this.roundedLogo = roundedLogo;
    }

    public List<RealmUser> getFriends() {
        return friends;
    }

    public String getFullName() {
        return surname + " " + name;
    }
}
