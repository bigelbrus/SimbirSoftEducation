package com.example.simbirsoftapp.data.net;

import android.util.Log;

import androidx.annotation.NonNull;


import com.example.simbirsoftapp.data.exeption.NoSuchDataExeption;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import io.reactivex.MaybeEmitter;

public class RxFirebaseHandler<T> implements OnSuccessListener<T>, OnCompleteListener<T>, OnFailureListener {
    private MaybeEmitter<? super T> emitter;

    private RxFirebaseHandler(MaybeEmitter<? super T> emitter) {
        this.emitter = emitter;
    }

    public static <T> void assignOnTask(MaybeEmitter<? super T> emitter, Task<T> task) {
        RxFirebaseHandler<T> handler = new RxFirebaseHandler<>(emitter);
        task.addOnSuccessListener(handler);
        task.addOnFailureListener(handler);
        task.addOnCompleteListener(handler);
        Log.d("tag","task assigned");
    }

    @Override
    public void onComplete(@NonNull Task<T> task) {
        Log.d("tag","complete");
        emitter.onComplete();
    }

    @Override
    public void onFailure(@NonNull Exception e) {
        if (!emitter.isDisposed())
            emitter.onError(e);
    }

    @Override
    public void onSuccess(T t) {
        Log.d("tag","success");
        if (t != null) {
            emitter.onSuccess(t);
        } else {
            emitter.onError(new NoSuchDataExeption("No such data in firebase"));
        }
    }
}