package com.example.simbirsoftapp.data.firebase;


import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import io.reactivex.Maybe;

public class RxFirebaseClass {
    private RxFirebaseClass(){}

    public static Maybe<AuthResult> signInWithEmailAndPass(String email, String pass) {
        return Maybe.create(emitter -> RxFirebaseHandler.assignOnTask(emitter, FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pass)));
    }
}
