package com.example.albert.myapplication.model;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Cow.class},version = 1)
public abstract class CowsDatabase extends RoomDatabase {
    public abstract CowDAO daoAccess();
}