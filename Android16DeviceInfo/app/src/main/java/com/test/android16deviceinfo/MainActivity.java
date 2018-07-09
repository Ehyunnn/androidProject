package com.test.android16deviceinfo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_READ_PHONE_STATE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_PHONE_STATE},
                    REQUEST_CODE_READ_PHONE_STATE);
        }

        TelephonyManager mTelephonyManager =
                (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        Log.i("Main Log", "" + mTelephonyManager.getLine1Number());

        //1.
        WindowManager mWindowManager =
                (WindowManager) getSystemService(WINDOW_SERVICE);
        Display mDisplay = mWindowManager.getDefaultDisplay();
        Log.i("Main Log", "" + mDisplay.getWidth());
        Log.i("Main Log", "" + mDisplay.getHeight());

        //2.
        DisplayMetrics mDisplayMetrics =
                getResources().getDisplayMetrics();
        Log.i("Main Log", "" + mDisplayMetrics.widthPixels);
        Log.i("Main Log", "" + mDisplayMetrics.heightPixels);

        //4.
        Log.i("Main Log", "" + Build.MODEL);
        Log.i("Main Log", "" + Build.BRAND);
        Log.i("Main Log", "" + Build.DEVICE);
        Log.i("Main Log", "" + Build.SERIAL);
    }
}
