package com.example.simbirsoftapp.data.net;

import android.util.Log;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.StorageReference;

import io.reactivex.Maybe;

public class RxFirebaseUtils {

    private RxFirebaseUtils(){}

    public static Maybe<byte[]> getBytes(StorageReference ref,long maxBytes) {
        Maybe<byte[]> maybe = Maybe.create(emitter -> RxFirebaseHandler.assignOnTask(emitter,ref.getBytes(maxBytes)));
        return maybe;
    }
}
