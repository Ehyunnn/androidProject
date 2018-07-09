package com.test.android28receiver;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button start_service;
    private Button stop_service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "SMS_Receiver start...", Toast.LENGTH_SHORT).show();


        start_service = (Button) findViewById(R.id.start_service);
        stop_service = (Button) findViewById(R.id.stop_service);
        start_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startService(new Intent(getApplicationContext(), AlwaysOnTopService.class));
                if (Build.VERSION.SDK_INT >= 23) {
                    if (!Settings.canDrawOverlays(MainActivity.this)) {
                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                Uri.parse("package:" + getPackageName()));
                        startActivityForResult(intent, 1234);

                    }
                } else {
                    Intent intent = new Intent(MainActivity.this, AlwaysOnTopService.class);
                    startService(intent);
                }
            }
        });
        stop_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(getApplicationContext(), AlwaysOnTopService.class));
            }
        });


        //finish();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECEIVE_SMS},
                        1111);
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent intent = new Intent(MainActivity.this, AlwaysOnTopService.class);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "MainActivity end...", Toast.LENGTH_SHORT).show();
    }

}
