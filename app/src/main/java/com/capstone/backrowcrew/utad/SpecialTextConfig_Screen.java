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
public class SpecialTextConfig_Screen extends Contacts_Screen {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.special_text_config_screen);

        //david edit
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final SharedPreferences.Editor editor = sharedPref.edit();
        //david edit


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
                //david's edit within
                Intent toSettings = new Intent(view.getContext(), Settings_Screen.class);
                TextView messageView = (TextView)findViewById(R.id.textConfig);
                String message = messageView.getText().toString();
                //access listSelectedContacts for contacts that need custom text saved to them.
                //if the message is empty string remove it from sharedpreferences (this will force it to use the default).
                if(listSelectedContacts.size() != 0){
                    for(int i = 0; i < listSelectedContacts.size(); i++){
                        if(message.compareTo("") == 0){
                            editor.remove(listSelectedContacts.get(i).getNumber());
                            editor.apply();
                        }
                        else {
                            String num = listSelectedContacts.get(i).getNumber();
                            Toast.makeText(getApplicationContext(),num + " : " + message, Toast.LENGTH_LONG).show();
                            editor.putString(num, message);
                            editor.apply();
                        }
                    }
                }

                listSelectedContacts.clear();
                startActivity(toSettings);
                //david's edit within
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
