package com.example.simbirsoftapp.data.exeption;

import androidx.annotation.NonNull;

public class NoSuchDataExeption extends Exception {
    private final String message;

    public NoSuchDataExeption(String message) {
        this.message = message;
    }

    @NonNull
    @Override
    public String toString() {
        return message;
    }
}
