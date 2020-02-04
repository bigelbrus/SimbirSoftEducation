package com.example.simbirsoftapp.data.model;

import com.example.simbirsoftapp.data.database.RealmEvent;
import com.example.simbirsoftapp.data.database.RealmUser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Event implements Serializable {
    private int id;
    private String eventName;
    private String eventDate;
    private String eventCompany;
    private String eventAddress;
    private List<String> organisationTelephone = new ArrayList<>();
    private List<String> eventPhoto = new ArrayList<>();
    private String eventDescription;
    private String organisationSite;
    private List<User> eventPerson = new ArrayList<>();

    public Event(RealmEvent realmEvent) {
        id = realmEvent.getId();
        eventName = realmEvent.getEventName();
        eventDate = realmEvent.getEventDate();
        eventCompany = realmEvent.getEventCompany();
        eventAddress = realmEvent.getEventAddress();
        organisationTelephone.addAll(realmEvent.getOrganisationTelephone());
        eventPhoto.addAll(realmEvent.getEventPhoto());
        eventDescription = realmEvent.getEventDescription();
        organisationSite = realmEvent.getOrganisationSite();
        for(RealmUser u:realmEvent.getEventPerson()) {
            eventPerson.add(new User(u));
        }
    }

    public Event(){}


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

    public List<String> getOrganisationTelephone() {
        return organisationTelephone;
    }

    public void setOrganisationTelephone(List<String> organisationTelephone) {
        this.organisationTelephone = organisationTelephone;
    }

    public void addOrganisationTelephone(String telephone) {
        this.organisationTelephone.add(telephone);
    }

    public List<String> getEventPhoto() {
        return eventPhoto;
    }

    public void setEventPhoto(List<String> eventPhoto) {
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

    public List<User> getEventPerson() {
        return eventPerson;
    }

    public void setEventPerson(List<User> eventPerson) {
        this.eventPerson = eventPerson;
    }

    public void addEventPerson(User person) {
        eventPerson.add(person);
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }
}
