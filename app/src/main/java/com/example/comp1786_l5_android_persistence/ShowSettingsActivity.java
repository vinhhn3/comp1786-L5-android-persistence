package com.example.comp1786_l5_android_persistence;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

public class ShowSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_settings);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String strPrefs =
                sharedPreferences.getBoolean(
                        "perform_updates", false) + "\n"
                        + sharedPreferences.getString("updates_interval", "-1") + "\n"
                        + sharedPreferences.getString("welcome_message", "NULL"
                );
        TextView settingsTextView = (TextView) findViewById(R.id.settings_text_view);
        settingsTextView.setText(strPrefs);
    }
}