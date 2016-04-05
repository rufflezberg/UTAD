package com.example.russell.utad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Russell on 4/5/2016.
 */
public class TextConfig_Screen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_config_screen);

        //navigation to settings when save button is pressed
        Button saveButton=(Button)findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent toSettings = new Intent(view.getContext(), Settings_Screen.class);
                startActivity(toSettings);
            }
        });
    }
}
