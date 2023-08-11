package com.example.comp1786_l5_android_persistence.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.comp1786_l5_android_persistence.R;
import com.example.comp1786_l5_android_persistence.adapters.ContactAdapter;
import com.example.comp1786_l5_android_persistence.database.AppDatabase;
import com.example.comp1786_l5_android_persistence.models.Person;

import java.util.List;


public class DetailsActivity extends AppCompatActivity {
    private AppDatabase appDatabase;
    private RecyclerView recyclerView;
    private ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "sqlite_example_db")
                .allowMainThreadQueries() // For simplicity, don't use this in production
                .build();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Person> persons = appDatabase.personDao().getAllPersons();

        adapter = new ContactAdapter(persons);
        recyclerView.setAdapter(adapter);
    }
}
