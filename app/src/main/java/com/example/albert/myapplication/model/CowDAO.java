package com.example.albert.myapplication.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface CowDAO {
    @Insert
    void addNote(Cow cow);

    @Query("SELECT * FROM cow WHERE id = :id")
    Cow getCowbyID(int id);

    @Query("SELECT * FROM cow")
    List<Cow> getCows();

    @Update
    void update(Cow cow);
}