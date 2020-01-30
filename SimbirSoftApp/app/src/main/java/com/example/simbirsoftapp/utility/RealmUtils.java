package com.example.simbirsoftapp.utility;

import io.realm.RealmConfiguration;

public class RealmUtils {
    private static final int SCHEMA_VERSION = 3;

    private RealmUtils(){}

    public static int getSchemaVNow() {
        return SCHEMA_VERSION;
    }


    public static RealmConfiguration getDefaultConfig() {
        return new RealmConfiguration.Builder()
                .schemaVersion(SCHEMA_VERSION)
                .deleteRealmIfMigrationNeeded()
                .build();
    }
}
