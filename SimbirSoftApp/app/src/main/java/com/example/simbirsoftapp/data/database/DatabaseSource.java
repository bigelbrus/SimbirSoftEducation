package com.example.simbirsoftapp.data.database;


import com.example.simbirsoftapp.utility.RealmUtils;

import io.realm.Realm;

public class DatabaseSource {
    private static DatabaseSource instance;
    private Realm realm;

    private DatabaseSource() {
        realm = Realm.getInstance(RealmUtils.getDefaultConfig());
    }

    public static DatabaseSource getInstance() {
        if (instance == null) {
            instance = new DatabaseSource();
        }
        return instance;
    }

    public Realm getRealm() {
        return realm;
    }
}
