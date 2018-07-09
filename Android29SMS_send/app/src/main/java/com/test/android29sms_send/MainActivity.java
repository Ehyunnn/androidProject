package com.test.android29sms_send;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private String PhoneNum;
    private static final int REQUEST_CODE_LOCATION = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            // Request missing SEND_SMS permission.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    REQUEST_CODE_LOCATION);
        }


        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneNum = "01038589494";
                SmsManager mSmsManager = SmsManager.getDefault();
                mSmsManager.sendTextMessage(
                        PhoneNum, null,
                        "전화해~~~~^^",
                        null, null);
            }
        });




    }

//
//    public void testClick(View v){
//        PhoneNum = "01038589494";
//        SmsManager mSmsManager = SmsManager.getDefault();
//        mSmsManager.sendTextMessage(
//                PhoneNum, null,
//                "전화해~~~~^^",
//                null, null);
//    }

}
