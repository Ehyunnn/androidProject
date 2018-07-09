package com.test.android27service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_startService;
    private Button btn_stopService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_startService = (Button)findViewById(R.id.startService);
        btn_stopService = (Button)findViewById(R.id.stopService);


        btn_startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(
                        getApplicationContext(),
                        TestService.class));
            }
        });
        btn_stopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(
                        getApplicationContext(),
                        TestService.class));
            }
        });

    }
}
