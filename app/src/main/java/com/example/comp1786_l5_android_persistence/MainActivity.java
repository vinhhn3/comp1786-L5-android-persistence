package com.example.comp1786_l5_android_persistence;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private static final String NOTES_FILE_NAME = "notes.txt";

    @Override
    public void onPause() {
        // Override the onPause() method and call the version in the super class
        super.onPause();

        try {
            // Create a private file, using the file name constant
            FileOutputStream out = openFileOutput(NOTES_FILE_NAME, Context.MODE_PRIVATE);

            // Get a reference to the EditText view
            EditText textEditor = findViewById(R.id.textEditor);

            // Add the content of the EditText view to the file
            out.write(textEditor.getText().toString().getBytes());

            // Close the output stream
            out.close();
        } catch (Exception e) {
            // Should be in a try/catch as with file I/O things can go wrong
            Toast
                    .makeText(this, "Exception " + e.toString(), Toast.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
            // Opens the file using the same file name
            FileInputStream in = openFileInput(NOTES_FILE_NAME);
            if (in != null) {
                // If the file exists, create an InputStreamReader
                InputStreamReader tmp = new InputStreamReader(in);

                // Create a BufferedReader from the StreamReader
                BufferedReader reader = new BufferedReader(tmp);

                // Create a StringBuilder
                StringBuilder buf = new StringBuilder();

                // Some text is added, to show the text was loaded from file – not a good idea in real life
                buf.append("Restored content: \n");

                // Add all content to the StringBuilder
                String str = reader.readLine();
                while (str != null) {
                    buf.append(str + "\n");
                    str = reader.readLine();
                }

                in.close();
                EditText textEditor = findViewById(R.id.textEditor);

                // Display the content of the StringBuilder in the EditText view
                textEditor.setText(buf.toString());
            }
        } catch (FileNotFoundException e) {
        } catch (Exception e) {
            // Again, should be in try/catch
            Toast.makeText(this, "Exception " + e.toString(),
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}