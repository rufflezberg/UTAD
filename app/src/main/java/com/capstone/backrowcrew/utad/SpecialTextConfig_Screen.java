package com.capstone.backrowcrew.utad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Russell on 4/5/2016.
 */
public class SpecialTextConfig_Screen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.special_text_config_screen);

        //navigation to contacts when set contact button is pressed
        Button setContactButton=(Button)findViewById(R.id.setContact);
        setContactButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent toSetContacts = new Intent(view.getContext(), Contacts_Screen.class);
                startActivity(toSetContacts);
            }
        });

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
