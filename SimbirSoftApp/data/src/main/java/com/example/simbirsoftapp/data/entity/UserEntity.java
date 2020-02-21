package com.example.simbirsoftapp.data.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.simbirsoftapp.data.utils.DateUtils;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "user")
public class UserEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String surname;
    private LocalDate date;
    private String activity;
    private boolean wantPush;
    private int logo;
    private String roundedLogo;
    private List<UserEntity> friends = new ArrayList<>();

    public UserEntity() {
    }
    @Ignore
    public UserEntity(String name, String surname, int logo) {
        this.name = name;
        this.surname = surname;
        this.logo = logo;
    }


    @Ignore
    public UserEntity(int id,String name, String surname, String activity, boolean wantPush, int logo,
                List<UserEntity> friends, String date) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.activity = activity;
        this.wantPush = wantPush;
        this.logo = logo;
        this.friends = friends;
        this.date = DateUtils.parseDate(date);
    }

//    public User(RealmUser u) {
//        name = u.getName();
//        surname = u.getSurname();
//        date = (u.getDate() != null) ? Instant.ofEpochMilli(u.getDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate() :
//                null;
//        activity = u.getActivity();
//        wantPush = u.isWantPush();
//        logo = u.getLogo();
//        roundedLogo = u.getRoundedLogo();
//        for (RealmUser f : u.getFriends()) {
//            friends.add(new User(f));
//        }
//    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void addFriend(UserEntity p) {
        friends.add(p);
    }

    public String getRoundedLogo() {
        return roundedLogo;
    }

    public void setRoundedLogo(String roundedLogo) {
        this.roundedLogo = roundedLogo;
    }

    public List<UserEntity> getFriends() {
        return friends;
    }

    public void setFriends(List<UserEntity> friends) {
        this.friends = friends;
    }

    public String getFullName() {
        return surname + " " + name;
    }
}
