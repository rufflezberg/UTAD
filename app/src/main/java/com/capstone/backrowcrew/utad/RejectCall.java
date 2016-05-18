package com.capstone.backrowcrew.utad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.lang.reflect.Method;

/**
 * Created by lesst on 5/17/2016.
 */
public class RejectCall extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Toast.makeText(context, "bitch", Toast.LENGTH_LONG).show();
        if(!intent.getAction().equals("android.intent.action.PHONE_STATE"))
            return;

        else
        {
            if(Home_Screen.driveModeIsOn)
            {
                disconnectPhoneITelephony(context);
                return;
            }

            else
                return;
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void disconnectPhoneITelephony(Context context)
    {
        ITelephony telephonyService;
        TelephonyManager telephony = (TelephonyManager)
                context.getSystemService(Context.TELEPHONY_SERVICE);


        try
        {
            Class c = Class.forName(telephony.getClass().getName());
            Method m = c.getDeclaredMethod("getITelephony");
            m.setAccessible(true);
            telephonyService = (ITelephony) m.invoke(telephony);
            telephonyService.endCall();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

interface ITelephony
{
    boolean endCall();
}
