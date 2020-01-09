package com.example.simbirsoftapp.data.database;

import com.example.simbirsoftapp.data.model.Event;
import com.example.simbirsoftapp.data.model.User;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmEvent extends RealmObject {
    @PrimaryKey
    private int id;
    private String eventName;
    private String eventDate;
    private String eventCompany;
    private String eventAddress;
    private RealmList<String> organisationTelephone = new RealmList<>();
    private RealmList<String> eventPhoto = new RealmList<>();
    private String eventDescription;
    private String organisationSite;
    private RealmList<RealmUser> eventPerson = new RealmList<>();

    public RealmEvent(Event e) {
        id = e.getId();
        eventName = e.getEventName();
        eventDate = e.getEventDate();
        eventCompany = e.getEventCompany();
        eventAddress = e.getEventAddress();
        organisationTelephone.addAll(e.getOrganisationTelephone());
        eventPhoto.addAll(e.getEventPhoto());
        eventDescription = e.getEventDescription();
        organisationSite = e.getOrganisationSite();
        for (User u: e.getEventPerson()) {
            eventPerson.add(new RealmUser(u));
        }
    }

    public RealmEvent(){}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventCompany() {
        return eventCompany;
    }

    public void setEventCompany(String eventCompany) {
        this.eventCompany = eventCompany;
    }

    public String getEventAddress() {
        return eventAddress;
    }

    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }

    public RealmList<String> getOrganisationTelephone() {
        return organisationTelephone;
    }

    public void setOrganisationTelephone(RealmList<String> organisationTelephone) {
        this.organisationTelephone = organisationTelephone;
    }

    public void addOrganisationTelephone(String telephone) {
        this.organisationTelephone.add(telephone);
    }

    public RealmList<String> getEventPhoto() {
        return eventPhoto;
    }

    public void setEventPhoto(RealmList<String> eventPhoto) {
        this.eventPhoto = eventPhoto;
    }

    public void addEventPhoto(String photo) {
        eventPhoto.add(photo);
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getOrganisationSite() {
        return organisationSite;
    }

    public void setOrganisationSite(String organisationSite) {
        this.organisationSite = organisationSite;
    }

    public RealmList<RealmUser> getEventPerson() {
        return eventPerson;
    }

    public void setEventPerson(RealmList<RealmUser> eventPerson) {
        this.eventPerson = eventPerson;
    }

    public void addEventPerson(RealmUser person) {
        eventPerson.add(person);
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }


}
