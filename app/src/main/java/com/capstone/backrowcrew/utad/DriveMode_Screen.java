package com.capstone.backrowcrew.utad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Russell on 4/3/2016.
 */
public class DriveMode_Screen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drivemode_screen);

        //navigation to home screen when save button is pressed
        Button exitDriveModeButton=(Button)findViewById(R.id.exitDriveMode);
        exitDriveModeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent toHomeScreen = new Intent(view.getContext(), Home_Screen.class);
                startActivity(toHomeScreen);
            }
        });
    }
}
