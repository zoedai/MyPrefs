package com.example.dai.myprefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;
    Button save;
    String name;
    Boolean sound;
    int threshold;
    public final String NAME_DEFAULT = "Guest";
    public final boolean SOUND_DEFAULT = false;
    public final int THRESHOLD_DEFAULT = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        preferences = getPreferences(Context.MODE_PRIVATE);

        // get saved value, if not exist, use default
        name = preferences.getString(getString(R.string.saved_name), NAME_DEFAULT);
        sound = preferences.getBoolean(getString(R.string.saved_sound), SOUND_DEFAULT);
        threshold = preferences.getInt(getString(R.string.saved_threshold), THRESHOLD_DEFAULT);

        showCurrentSettings();

        save = (Button) findViewById(R.id.saveBtn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();
                name = ((EditText) findViewById(R.id.editText)).getText().toString();
                sound = ((ToggleButton) findViewById(R.id.toggleButton)).isChecked();
                threshold = ((SeekBar) findViewById(R.id.seekBar)).getProgress();

                editor.putString(getString(R.string.saved_name), name);
                editor.putBoolean(getString(R.string.saved_sound), sound);
                editor.putInt(getString(R.string.saved_threshold), threshold);

                editor.commit();

                showCurrentSettings();
            }
        });

    }

    private void showCurrentSettings() {
        Toast.makeText(this, String.format("Name: %s, Sound: %s, threshold: %s", name, sound, threshold),
                Toast.LENGTH_LONG).show();
    }
}
