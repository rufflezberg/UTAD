package com.capstone.backrowcrew.utad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home_Screen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

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
    }
}
