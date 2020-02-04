package com.example.simbirsoftapp.data.firebase;

import android.util.Log;

import com.google.firebase.storage.StorageReference;

import io.reactivex.Maybe;

public class RxFirebaseStorage {
    public static Maybe<byte[]> getBytes(StorageReference ref,long maxBytes) {
        Maybe<byte[]> maybe = Maybe.create(emitter -> RxFirebaseHandler.assignOnTask(emitter,ref.getBytes(maxBytes)));
        Log.d("tag","maybe success");
        return maybe;
    }
}
