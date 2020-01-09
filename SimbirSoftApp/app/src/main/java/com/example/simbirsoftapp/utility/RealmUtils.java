package com.example.simbirsoftapp.utility;

import io.realm.RealmConfiguration;

public class RealmUtils {
    private static final int SCHEMA_V_FIRST = 1;
    private static final int SCHEMA_V_SECOND = 2;
    private static final int SCHEMA_V_THIRD = 3;



    public static int getSchemaVNow() {
        return SCHEMA_V_THIRD;
    }


    public static RealmConfiguration getDefaultConfig() {
        return new RealmConfiguration.Builder()
                .schemaVersion(SCHEMA_V_THIRD)
                .deleteRealmIfMigrationNeeded()
                .build();
    }
}
