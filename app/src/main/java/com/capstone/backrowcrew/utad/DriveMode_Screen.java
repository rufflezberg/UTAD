package com.capstone.backrowcrew.utad;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
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

        AudioManager audioManager= (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        driveModeIsOn = true;
        if(phoneIsOn && textIsOn){
            Toast.makeText(getBaseContext(), "Calls and texts are being filtered, and notifications have been silenced!", Toast.LENGTH_LONG).show();
            //calls and texts filter code goes here
        }
        else if(phoneIsOn && !textIsOn){
            Toast.makeText(getBaseContext(), "Only calls are being filtered, and notifications have been silenced!", Toast.LENGTH_LONG).show();
            //only calls filter code goes here
        }
        else if(!phoneIsOn && textIsOn){
            Toast.makeText(getBaseContext(), "Only texts are being filtered, and notifications have been silenced!", Toast.LENGTH_LONG).show();
            //only texts filter code goes here
        }
        else if(!phoneIsOn && !textIsOn){
            Toast.makeText(getBaseContext(), "Neither calls nor texts are being filtered, but notifications have been silenced!", Toast.LENGTH_LONG).show();
            //only notification silence
        }


        //navigation to home screen when save button is pressed
        Button exitDriveModeButton=(Button)findViewById(R.id.exitDriveMode);
        exitDriveModeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent toHomeScreen = new Intent(view.getContext(), Home_Screen.class);
                AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
                audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                startActivity(toHomeScreen);
            }
        });
    }
}
