package com.example.comp1786_l5_android_persistence;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Also declares an object of our helper class
        DatabaseHelper db = new DatabaseHelper(this);

        // Call the getDetails method we created
        String details = db.getDetails();

        TextView detailsTxt = findViewById(R.id.detailsText);

        // The text returned is just displayed
        detailsTxt.setText(details);
    }
}