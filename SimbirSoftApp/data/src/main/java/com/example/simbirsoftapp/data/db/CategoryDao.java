package com.example.simbirsoftapp.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.simbirsoftapp.data.entity.CategoryEntity;
import com.example.simbirsoftapp.data.entity.EventEntity;

import java.util.List;

@Dao
public interface CategoryDao {
    @Query("Select * from category")
    List<CategoryEntity> getCategories();

    @Insert
    void insertCategory(CategoryEntity categoryEntity);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateCategory(CategoryEntity categoryEntity);

    @Query("Select * from event")
    List<EventEntity> getEvents();

    @Insert
    void insertEvent (EventEntity eventEntity);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateEvent(EventEntity eventEntity);
}
