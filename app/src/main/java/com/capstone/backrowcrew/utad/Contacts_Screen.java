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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Russell on 5/14/2016.
 */
public class Contacts_Screen extends Activity {

    private ListView list;
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
        selectButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){


                Intent toSpecialTextConfig = new Intent(view.getContext(), SpecialTextConfig_Screen.class);
                listSelectedContacts = new ArrayList<Contact>();
                if(contactStrings.size() == 0){
                    startActivity(toSpecialTextConfig);
                }
                else {
                    for (int i = 0; i < contactStrings.size(); i++) {
                        String[] strings = contactStrings.get(i).split("\n");
                        String nam = strings[0];
                        String num = strings[1];
                        listSelectedContacts.add(new Contact(nam, num));
                    }
                    startActivity(toSpecialTextConfig);
                }
            }
        });

        String[] query = new String[] {ContactsContract.Contacts.Data._ID, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
        ContentResolver cr = this.getContentResolver();
        Cursor c = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, query, null, null, null);
        c.moveToFirst();
        name = c.getString(1);
        number = c.getString(2);
        if(name.compareTo(number) == 0){
            name = "";
        }
        for(int i = 0; i < number.length(); i++){
            if(!Character.isDigit(number.charAt(i))){
                StringBuilder sb = new StringBuilder(number);
                sb.deleteCharAt(i);
                number = sb.toString();
            }
        }
        contactsList.add(new Contact(name, number));
        while(c.moveToNext()){
            name = c.getString(1);
            number = c.getString(2);
            if(name.compareTo(number) == 0){
                name = "";
            }
            for(int i = 0; i < number.length(); i++){
                if(!Character.isDigit(number.charAt(i))){
                    StringBuilder sb = new StringBuilder(number);
                    sb.deleteCharAt(i);
                    number = sb.toString();
                }
            }
            contactsList.add(new Contact(name, number));
        }
        List<String> stringContacts = new ArrayList<String>();
        for(int i = 0; i < contactsList.size(); i++){
            stringContacts.add(contactsList.get(i).toString());
        }

        adapter = new ArrayAdapter<String>(this, R.layout.contacts_text_view, stringContacts);
        list = (ListView)findViewById(R.id.contacts_list);
        list.setAdapter(adapter);
        list.setBackgroundColor(Color.WHITE);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = list.getItemAtPosition(position);
                String s = o.toString();
                boolean isFound = false;
                for(int i = 0; i < contactStrings.size(); i++){
                    if(s.compareTo(contactStrings.get(i)) == 0){
                        contactStrings.remove(i);
                        view.setBackgroundColor(Color.WHITE);
                        isFound = true;
                        count_selected--;
                    }
                }
                if(!isFound){
                    contactStrings.add(s);
                    view.setBackgroundColor(Color.parseColor("#0099ff"));
                    count_selected++;
                }
                if(count_selected > 0){
                    selectButton.setText("Select");
                }
                else if(count_selected == 0){
                    selectButton.setText("Back");
                }
                if(count_selected < 0){
                    count_selected = 0;
                }
            }
        });
    }
}
