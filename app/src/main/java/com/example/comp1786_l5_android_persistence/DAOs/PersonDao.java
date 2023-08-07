package com.example.comp1786_l5_android_persistence.DAOs;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.comp1786_l5_android_persistence.Models.Person;

import java.util.List;

@Dao
public interface PersonDao {
    @Insert
    long insertPerson(Person person);

    @Query("SELECT * FROM details ORDER BY name")
    List<Person> getAllPersons();
}
