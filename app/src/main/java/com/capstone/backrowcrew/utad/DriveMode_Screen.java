package com.capstone.backrowcrew.utad;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Created by Russell on 4/3/2016.
 */
public class DriveMode_Screen extends Home_Screen {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drivemode_screen);

        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final SharedPreferences.Editor editor = sharedPref.edit();

        AudioManager audioManager= (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);

        if(textIsOn){
            Toast.makeText(getBaseContext(), "Texts are being filtered, and notifications have been silenced!", Toast.LENGTH_LONG).show();
            editor.putBoolean("textIsOn",textIsOn);
            editor.apply();
        }
        else if(!textIsOn){
            Toast.makeText(getBaseContext(), "Only notifications have been silenced!", Toast.LENGTH_LONG).show();
            editor.putBoolean("textIsOn",textIsOn);
            editor.apply();
        }

        //navigation to home screen when save button is pressed
        Button exitDriveModeButton=(Button)findViewById(R.id.exitDriveMode);
        exitDriveModeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent toHomeScreen = new Intent(view.getContext(), Home_Screen.class);
                AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
                audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                editor.putBoolean("textIsOn",false);
                editor.apply();
                startActivity(toHomeScreen);
            }
        });
    }
}
