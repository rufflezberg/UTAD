package com.capstone.backrowcrew.utad;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Russell on 5/14/2016.
 */
public class Contacts_Screen extends Activity {

    private List<Contact> contactsList = new ArrayList<Contact>();
    private String name;
    private String number;
    private Contact cont = new Contact();
    private ArrayAdapter<String> adapter;
    private List<String> contactStrings = new ArrayList<String>();
    public static List<Contact> listSelectedContacts;
    private int count_selected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_screen);

        final Button selectButton = (Button)findViewById(R.id.select);
        //saves list of contacts and navigates to specialTextConfig_Screen
        selectButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent toSpecialTextConfig = new Intent(view.getContext(), SpecialTextConfig_Screen.class);
                //create new list for contacts
                listSelectedContacts = new ArrayList<Contact>();
                if(contactStrings.size() == 0){
                    //if no contacts were selected, just navigate back to specialTextConfig_Screen
                    startActivity(toSpecialTextConfig);
                }
                else {
                    for (int i = 0; i < contactStrings.size(); i++) {
                        //set name and number of contact then add to list before navigation
                        String[] strings = contactStrings.get(i).split("\n");
                        String nam = strings[0];
                        String num = strings[1];
                        listSelectedContacts.add(new Contact(nam, num));
                    }
                    startActivity(toSpecialTextConfig);
                }
            }
        });

        //query the phone data for the contacts
        String[] query = new String[] {ContactsContract.Contacts.Data._ID, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
        ContentResolver cr = this.getContentResolver();
        Cursor c = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, query, null, null, null);
        //first contact returned. Put name and number into a string
        c.moveToFirst();
        name = c.getString(1);
        number = c.getString(2);
        if(name.compareTo(number) == 0){
            //check if there is no name for the number
            name = "";
        }
        for(int i = 0; i < number.length(); i++){
            //checks to make sure phone number is comprised of entirely digits
            if(!Character.isDigit(number.charAt(i))){
                StringBuilder sb = new StringBuilder(number);
                sb.deleteCharAt(i);
                i--;
                number = sb.toString();
            }
        }
        number.replaceAll("\\s+","");

        LinearLayout list = (LinearLayout)findViewById(R.id.contacts_list);
        //add contacts to the list of contacts
        contactsList.add(new Contact(name, number));
        //create textViews for the list of textViews, set their text, and onclicklisteners, then add to the linearlayout
        TextView textView = new TextView(this);
        textView.setText(contactsList.get(0).toString());
        setClickListener(textView, textView.getText().toString(), selectButton);
        list.addView(textView);
        //count to represent index of contact in list
        int count = 1;
        while(c.moveToNext()){
            //same operations as were done on the first contact, except for all subsequent contacts
            name = c.getString(1);
            number = c.getString(2);
            if(name.compareTo(number) == 0){
                name = "";
            }
            for(int i = 0; i < number.length(); i++){
                if(!Character.isDigit(number.charAt(i))){
                    StringBuilder sb = new StringBuilder(number);
                    sb.deleteCharAt(i);
                    i--;
                    number = sb.toString();
                }
            }

            contactsList.add(new Contact(name, number));
            textView = new TextView(this);
            textView.setText(contactsList.get(count).toString());
            setClickListener(textView, textView.getText().toString(), selectButton);
            list.addView(textView);
            count++;
        }

        list.setBackgroundColor(Color.WHITE);
    }

    void setClickListener(TextView tView, final String s, final Button sButton){
        //set the onclicklisteners for the textViews in the linear layout
        tView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isFound = false;
                for(int i = 0; i < contactStrings.size(); i++){
                    //check to see if the contact is already selected by seeing if it's in the list of contacts selected or not
                    if(s.compareTo(contactStrings.get(i)) == 0){
                        //if the contact is already in the list, remove it and unhighlight it
                        contactStrings.remove(i);
                        view.setBackgroundColor(Color.WHITE);
                        isFound = true;
                        count_selected--;
                    }
                }
                if(!isFound){
                    //if the contact has not been selected yet, add it to the list and highlight it
                    contactStrings.add(s);
                    view.setBackgroundColor(Color.parseColor("#0099ff"));
                    count_selected++;
                }
                if(count_selected > 0){
                    //if one or more contacts have been selected, change button text to 'select'
                    sButton.setText("Select");
                }
                else if(count_selected == 0){
                    //if no contacts have been selected, change button text to 'back'
                    sButton.setText("Back");
                }
                if(count_selected < 0){
                    //if for some reason the count_selected falls below 0, set it back to 0
                    count_selected = 0;
                }
            }
        });
    }
}
