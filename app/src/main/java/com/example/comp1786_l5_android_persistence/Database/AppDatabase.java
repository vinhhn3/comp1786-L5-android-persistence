package com.example.comp1786_l5_android_persistence.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.comp1786_l5_android_persistence.DAOs.PersonDao;
import com.example.comp1786_l5_android_persistence.Models.Person;

@Database(entities = {Person.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PersonDao personDao();
}
