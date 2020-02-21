package com.example.simbirsoftapp.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.simbirsoftapp.data.entity.CategoryEntity;

@Database(entities = CategoryEntity.class,version = 2,exportSchema = false)
public abstract class RoomDb extends RoomDatabase {
    private static final String DATABASE_NAME = "need_help_app_db";
    private static RoomDb sInstance;


    public static RoomDb getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context,RoomDb.class,DATABASE_NAME).fallbackToDestructiveMigration().allowMainThreadQueries()
                    .build();
        }
        return sInstance;
    }

    public abstract CategoryDao categoryDao();
}
