package com.example.simbirsoftapp.data;

import androidx.annotation.NonNull;

public class NoSuchDataException extends Exception {
    private final String message;

    public NoSuchDataException(String message) {
        this.message = message;
    }

    @NonNull
    @Override
    public String toString() {
        return message;
    }
}
