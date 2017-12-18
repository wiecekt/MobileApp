package wiecekt.mobileapp.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("WCHODZI W RECEIVE");
        if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            System.out.printf("WCHODZI W IFA");
            String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            System.out.println("Ktos dzwoni: " + incomingNumber);
            Toast.makeText(context, "Call from: " + incomingNumber, Toast.LENGTH_LONG).show();
            //find by number contact name
            // send notification
            // databaseHandler.sendEventInformation();
        }
    }
}
