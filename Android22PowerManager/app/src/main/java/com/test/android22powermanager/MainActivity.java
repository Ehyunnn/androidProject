package com.test.android22powermanager;

import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    private PowerManager.WakeLock mWakeLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();

        PowerManager mPowerManager =
                (PowerManager) getSystemService(POWER_SERVICE);

        mWakeLock = mPowerManager.newWakeLock(
                PowerManager.FULL_WAKE_LOCK,
                "yangssem");
        mWakeLock.acquire();

    }

    @Override
    protected void onStop() {
        super.onStop();
        mWakeLock.release();
        mWakeLock = null;
    }


}
