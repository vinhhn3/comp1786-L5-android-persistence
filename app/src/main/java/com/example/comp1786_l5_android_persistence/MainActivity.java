package com.example.comp1786_l5_android_persistence;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceFragmentCompat;

public class MainActivity extends AppCompatActivity {

    public static class QuickPrefsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
            // Reads the preferences from our preferences resource file
            setPreferencesFromResource(R.xml.preferences, rootKey);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu
        getMenuInflater().inflate(R.menu.preferences_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            // Open ShowSettingsActivity
            startActivity(new Intent(this, ShowSettingsActivity.class));
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a reference from the toolbar
        Toolbar myToolBar = findViewById(R.id.toolbar);

        // Set toolbar as actionbar for the activity
        setSupportActionBar(myToolBar);

        // Fragments are displayed using a FragmentSupportManager
        getSupportFragmentManager()
                // Fragments are added to an activity’s view using fragment transactions
                .beginTransaction()
                // We are loading the preferences into the FrameLayout we created
                // Replace content with the QuickPrefsFragment inner class
                .replace(R.id.pref_content, new QuickPrefsFragment())
                // Calling the commit() method will display the automatically generated preference settings
                .commit();
    }
}