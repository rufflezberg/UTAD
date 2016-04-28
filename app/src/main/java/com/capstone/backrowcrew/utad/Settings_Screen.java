package com.capstone.backrowcrew.utad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Russell on 4/5/2016.
 */
public class Settings_Screen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_screen);

        //navigation to call config when configure call button is pressed
        Button callConfigButton=(Button)findViewById(R.id.callConfig);
        callConfigButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent toCallConfig = new Intent(view.getContext(), CallConfig_Screen.class);
                startActivity(toCallConfig);
            }
        });

        //navigation to text configure when configure text button is pressed
        Button textConfigButton=(Button)findViewById(R.id.textConfig);
        textConfigButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent toTextConfig = new Intent(view.getContext(), TextConfig_Screen.class);
                startActivity(toTextConfig);
            }
        });

        //navigation to text configure with contact when configure text with contact button is pressed
        Button specialTextButton=(Button)findViewById(R.id.specialTextConfig);
        specialTextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent toSpecialTextConfig = new Intent(view.getContext(), SpecialTextConfig_Screen.class);
                startActivity(toSpecialTextConfig);
            }
        });

        //navigation to help when help button is pressed
        Button helpButton=(Button)findViewById(R.id.help);
        helpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent toHelp = new Intent(view.getContext(), Help_Screen.class);
                startActivity(toHelp);
            }
        });

        //navigation to home when back button is pressed
        Button backButton=(Button)findViewById(R.id.toHome);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent toHome = new Intent(view.getContext(), Home_Screen.class);
                startActivity(toHome);
            }
        });
    }
}
