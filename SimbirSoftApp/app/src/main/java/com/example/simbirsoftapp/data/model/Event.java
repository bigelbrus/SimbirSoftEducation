package com.example.simbirsoftapp.data.model;

import java.util.List;

public class Event {
    private int id;
    private String eventName;
    private String eventDate;
    private String eventCompany;
    private String eventAddress;
    private String[] organisationTelephone;
    private List<String> eventPhoto;
    private String eventDescription;
    private String organisationSite;
    private List<User> eventPerson;

    public Event(String eventName, String eventDate, String eventCompany, String eventAddress,
                 String[] organisationTelephone, List<String> eventPhoto, String eventDescription,
                 String organisationSite, List<User> eventPerson) {
        this.eventAddress = eventAddress;
        this.eventCompany = eventCompany;
        this.eventDate = eventDate;
        this.eventDescription = eventDescription;
        this.eventName = eventName;
        this.organisationTelephone = organisationTelephone;
        this.eventPhoto = eventPhoto;
        this.organisationSite = organisationSite;
        this.eventPerson = eventPerson;
    }
    public Event(){}

    public int getId() {
        return id;
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

    public String[] getOrganisationTelephone() {
        return organisationTelephone;
    }

    public void setOrganisationTelephone(String[] organisationTelephone) {
        this.organisationTelephone = organisationTelephone;
    }

    public List<String> getEventPhoto() {
        return eventPhoto;
    }

    public void setEventPhoto(List<String> eventPhoto) {
        this.eventPhoto = eventPhoto;
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

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }
}
