package com.capstone.backrowcrew.utad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Russell on 4/5/2016.
 */
public class SpecialTextConfig_Screen extends Contacts_Screen {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.special_text_config_screen);

        Button contactSet = (Button)findViewById(R.id.setContact);
        contactSet.setOnClickListener(new View.OnClickListener(){
            public void onClick(View setContact){
                Intent toContactSelection = new Intent(setContact.getContext(), Contacts_Screen.class);
                startActivity(toContactSelection);
            }
        });

        //navigation to settings adding specially configured text to persistent data when save button is pressed
        Button saveButton=(Button)findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent toSettings = new Intent(view.getContext(), Settings_Screen.class);
                //access listSelectedContacts for contacts that need custom text saved to them.
                //if the size of listSelectedContacts is 0 make text that is in the box the default?
                listSelectedContacts.clear();
                startActivity(toSettings);
            }
        });

        //navigation back to settings without altering persistent data when back button is pressed
        Button backButton=(Button)findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent toSettings = new Intent(view.getContext(), Settings_Screen.class);
                startActivity(toSettings);
            }
        });
    }
}
