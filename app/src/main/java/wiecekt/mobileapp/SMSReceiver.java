package wiecekt.mobileapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        DatabaseController databaseController = new DatabaseController();
        // ogolnie moze zrobic klase do obslugi smsow i przychodzacych polaczen a tutaj tylko wywolac metody
        // tutaj zrob date i czas zeby bylo wiadomo kiedy ten sms przyszedl

        System.out.println("wchodze w onreceive===================================");

        Bundle bundle = intent.getExtras();
        SmsMessage sms = null;
        String sender = "";
        String textMessage = "";
        String format = "";

        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            format = bundle.getString("format");

            for (int i = 0; i < pdus.length; i++) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    sms = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                } else {
                    sms = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }

                sender = sms.getDisplayOriginatingAddress();
                textMessage += sms.getMessageBody();
            }

            EventDTO eventDTO = new EventDTO(sender, textMessage);
            databaseController.saveEvent(eventDTO);
            System.out.println("SMS od: " + sender);
            System.out.println("Treść: " + textMessage);

        }


/*        if (bundle != null) {
            final Object[] pdusObj = (Object[]) bundle.get("pdus");
            for(int i = 0; i < pdusObj.length; i++) {
                SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                String sender = sms.getDisplayOriginatingAddress();
                String message = sms.getDisplayMessageBody();
                System.out.println("SMS nr " + i + ": ");
                System.out.println("Od: " + sender);
                System.out.println("Treść: " + message);
            }
        } else {
            System.out.println("dostalem nulla");
        }*/
    }
}
