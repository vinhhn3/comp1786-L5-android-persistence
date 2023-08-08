package com.example.comp1786_l5_android_persistence.activities;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.comp1786_l5_android_persistence.R;
import com.example.comp1786_l5_android_persistence.adapters.ContactAdapter;
import com.example.comp1786_l5_android_persistence.database.AppDatabase;
import com.example.comp1786_l5_android_persistence.models.Person;

import java.util.List;


public class DetailsActivity extends AppCompatActivity implements ContactAdapter.OnDeleteClickListener {
    private AppDatabase appDatabase;
    private RecyclerView recyclerView;
    private ContactAdapter adapter;

    List<Person> persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "details_db")
                .allowMainThreadQueries() // For simplicity, don't use this in production
                .build();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        persons = appDatabase.personDao().getAllPersons();
        adapter = new ContactAdapter(persons, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDeleteClick(Person person) {
        new AlertDialog.Builder(this)
                .setTitle("Delete Contact")
                .setMessage("Are you sure you want to delete this contact?")
                .setPositiveButton("Delete", (dialog, which) -> {
                    // Remove from the database
                    appDatabase.personDao().deletePerson(person);

                    // Update the list after deletion
                    persons.remove(person);
                    adapter.notifyDataSetChanged();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
