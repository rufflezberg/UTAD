package com.example.russell.utad;

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

        Button driveModeButton=(Button)findViewById(R.id.driveMode);
        driveModeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent toDriveMode = new Intent(view.getContext(), DriveMode_Screen.class);
                startActivity(toDriveMode);
            }
        });
    }
}
