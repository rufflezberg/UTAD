package com.capstone.backrowcrew.utad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by abber on 5/17/2016.
 */
public class SmsBroadcasterReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        //SharedPreferences.Editor editor = sharedPref.edit();

        String defaultMessage = sharedPref.getString("defaultMessage","no");

        if(sharedPref.getBoolean("textIsOn",false)) {
            //Toast.makeText(context, sharedPref.getBoolean("textIsOn",false) + "", Toast.LENGTH_LONG).show();
            Bundle intentExtras = intent.getExtras();

            if (intentExtras != null) {
            /* Get Messages */
                Object[] sms = (Object[]) intentExtras.get("pdus");
                String phone = "";
                String message = "";
                for (int i = 0; i < sms.length; ++i) {
                /* Parse Each Message */
                    SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);

                    phone = smsMessage.getOriginatingAddress();
                    message = sharedPref.getString(phone.toString(),defaultMessage);
                }
                sendSMS(phone,message,context);

            }
        }
    }

    protected void sendSMS(String phone, String message, Context context) {
        //String toPhoneNumber = toPhoneNumberET.getText().toString();
        //String smsMessage = smsMessageET.getText().toString();
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone, null, message, null, null);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
