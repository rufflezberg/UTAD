package com.capstone.backrowcrew.utad;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Russell on 4/5/2016.
 */
public class TextConfig_Screen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_config_screen);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final SharedPreferences.Editor editor = sharedPref.edit();

        //navigation to settings when save button is pressed
        Button saveButton=(Button)findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent toSettings = new Intent(view.getContext(), Settings_Screen.class);
                TextView messageView = (TextView)findViewById(R.id.textConfig);
                String message = messageView.getText().toString();

                editor.putString("defaultMessage",message);
                editor.apply();
                Toast.makeText(getBaseContext(), "Default text message saved!", Toast.LENGTH_SHORT).show();
                startActivity(toSettings);
            }
        });

        //navigation to settings when back button is pressed
        Button backButton=(Button)findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent toSettings = new Intent(view.getContext(), Settings_Screen.class);
                startActivity(toSettings);
            }
        });
    }
}
