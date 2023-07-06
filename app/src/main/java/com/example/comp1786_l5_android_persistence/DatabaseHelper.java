package com.example.comp1786_l5_android_persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "details";

    // A set of constants to store the column and table names
    public static final String ID_COLUMN = "person_id";
    public static final String NAME_COLUMN = "name";
    public static final String DOB_COLUMN = "dob";
    public static final String EMAIL_COLUMN = "email";

    private SQLiteDatabase database;

    private static final String DATABASE_CREATE = String.format(
            // The SQL query to create the table
            // %s expects a value of any type
            "CREATE TABLE %s (" +
                    "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT)",
            DATABASE_NAME, ID_COLUMN, NAME_COLUMN, DOB_COLUMN, EMAIL_COLUMN
    );

    // The constructor makes a call to the method in the super class, passing the database name
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        database = getWritableDatabase();
    }

    // Overriding the onCreate() method which generates the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    // This method upgrades the database if the version number changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);

        Log.v(this.getClass().getName(), DATABASE_NAME +
                "database upgrade to version" + newVersion + " - old data lost"
        );
        onCreate(db);
    }

    // Returns the automatically generated primary key
    public long insertDetails(String name, String dob, String email) {
        // ContentValues represents a single table row as a key/value map
        ContentValues rowValues = new ContentValues();

        rowValues.put(NAME_COLUMN, name);
        rowValues.put(DOB_COLUMN, dob);
        rowValues.put(EMAIL_COLUMN, email);

        return database.insertOrThrow(
                DATABASE_NAME,
                // nullColumnHack specifies a column that will be set to null if the ContentValues argument contains no data
                null,
                // Inserts ContentValues into the database
                rowValues
        );
    }

    public String getDetails() {
        Cursor results = database.query(DATABASE_NAME,
                // Defines the query to execute
                new String[]{ID_COLUMN, NAME_COLUMN, DOB_COLUMN, EMAIL_COLUMN},
                null, null, null, null, NAME_COLUMN
        );
        String resultText = "";

        // Moves to the first position of the result set
        results.moveToFirst();

        // Checks whether there are more rows in the result set
        while (!results.isAfterLast()) {

            // Extracts the values from the row
            int id = results.getInt(0);
            String name = results.getString(1);
            String dob = results.getString(2);
            String email = results.getString(3);

            // Concatenates the text values
            resultText += id + " " + name + " " + dob + " " + email + "\n";

            // Moves to the next row in the result set
            results.moveToNext();
        }

        // Returns a long string of all results
        return resultText;
    }

}

