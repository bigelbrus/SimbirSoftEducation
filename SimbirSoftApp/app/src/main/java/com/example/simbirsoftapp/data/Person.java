package com.example.simbirsoftapp.data;

public class Person {
    private String name;
    private String surname;
    private int logo;

    public Person(String name, String surname, int logo) {
        this.name = name;
        this.surname = surname;
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getLogo() {
        return logo;
    }

    public String getFullName() {
        return surname + " " + name;
    }
}
