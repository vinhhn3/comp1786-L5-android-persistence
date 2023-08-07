package com.example.comp1786_l5_android_persistence.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "details")
public class Person {
    @PrimaryKey(autoGenerate = true)
    public long person_id;
    public String name;
    public String dob;
    public String email;
}
