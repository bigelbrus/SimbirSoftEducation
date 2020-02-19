package com.example.simbirsoftapp.data.net;

import android.util.Log;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.StorageReference;

import io.reactivex.Maybe;

public class RxFirebaseClass {

    private RxFirebaseClass(){}

    public static Maybe<byte[]> getBytes(StorageReference ref,long maxBytes) {
        Maybe<byte[]> maybe = Maybe.create(emitter -> RxFirebaseHandler.assignOnTask(emitter,ref.getBytes(maxBytes)));
        Log.d("tag","maybe success");
        return maybe;
    }

    public static Maybe<AuthResult> signInWithEmailAndPass(String email, String pass) {
        return Maybe.create(emitter -> RxFirebaseHandler.assignOnTask(emitter, FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pass)));
    }
}
