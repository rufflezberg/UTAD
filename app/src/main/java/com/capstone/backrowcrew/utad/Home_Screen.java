package com.capstone.backrowcrew.utad;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class Home_Screen extends Activity {

    static boolean phoneIsOn = false;
    static boolean textIsOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final SharedPreferences.Editor editor = sharedPref.edit();

        editor.putBoolean("phoneIsOn",false);
        editor.putBoolean("textIsOn",false);
        editor.apply();

        String checkString = sharedPref.getString("defaultMessage","no");
        if(checkString.compareTo("no") == 0){
            editor.putString("defaultMessage","I'm driving.");
            editor.apply();
        }

        final Switch phoneSwitch = (Switch)findViewById(R.id.filterCalls);
        if(phoneIsOn){
            phoneSwitch.toggle();
        }
        phoneSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    phoneIsOn = true;

                }
                else if(isChecked == false){
                    phoneIsOn = false;

                }
            }
        });

        Switch textSwitch = (Switch)findViewById(R.id.filterTexts);
        if(textIsOn){
            textSwitch.toggle();
        }
        textSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    textIsOn = true;

                }
                else if(isChecked == false){
                    textIsOn = false;

                }
            }
        });

        //navigation to drive mode when drive mode button is pressed
        Button driveModeButton=(Button)findViewById(R.id.driveMode);
        driveModeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent toDriveMode = new Intent(view.getContext(), DriveMode_Screen.class);
                startActivity(toDriveMode);
                //i am comment
            }
        });

        Button helpButton=(Button)findViewById(R.id.help);
        helpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent toHelp = new Intent(view.getContext(), Help_Screen.class);
                startActivity(toHelp);
            }
        });

        Button settingsButton=(Button)findViewById(R.id.settings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent toSettings = new Intent(view.getContext(), Settings_Screen.class);
                startActivity(toSettings);
            }
        });
        //this is a comment 
    }
}
