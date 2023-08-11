package com.example.comp1786_l5_android_persistence.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.comp1786_l5_android_persistence.R;
import com.example.comp1786_l5_android_persistence.database.AppDatabase;
import com.example.comp1786_l5_android_persistence.models.Person;

import java.util.List;


public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // For simplicity, don't use this in production
        AppDatabase appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "sqlite_example_db")
                .allowMainThreadQueries() // For simplicity, don't use this in production
                .build();

        TextView detailsTxt = findViewById(R.id.detailsText);

        List<Person> persons = appDatabase.personDao().getAllPersons();

        StringBuilder detailsBuilder = new StringBuilder();
        for (Person person : persons) {
            detailsBuilder.append(person.person_id).append(" ")
                    .append(person.name).append(" ")
                    .append(person.dob).append(" ")
                    .append(person.email).append("\n");
        }

        detailsTxt.setText(detailsBuilder.toString());
    }
}