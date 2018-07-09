package com.test.android28receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by yangssem 2018-04-06
 */
public class TestSMS_Receiver extends BroadcastReceiver {

    static String msg = "test";

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        String str = "";

        if (bundle != null) {
            Object[] pdus =
                    (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];

            for (int i = 0; i < msgs.length; i++) {
                msgs[i] = SmsMessage.createFromPdu(
                        (byte[]) pdus[i]);
                str += "senderNO:"
                        + msgs[i].getOriginatingAddress();
                str += "\n";
                str += msgs[i].getMessageBody().toString();
            }// end for
            Log.i("testLog",str);
            Toast t = new Toast(context);
            t.setGravity(Gravity.CENTER, 0, 0);
            t.setDuration(Toast.LENGTH_LONG);
            TextView tv = new TextView(context);
            tv.setTextSize(20.0f);
            tv.setText(str);
            tv.setBackgroundResource(
                    android.R.drawable.toast_frame);
            t.setView(tv);
            t.show();

            msg = str;
            context.stopService(new Intent(context, AlwaysOnTopService.class));
            context.startService(new Intent(context, AlwaysOnTopService.class));

        }
    }
}
