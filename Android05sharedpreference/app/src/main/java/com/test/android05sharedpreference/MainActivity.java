package com.test.android05sharedpreference;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sp =
                getSharedPreferences("test",MODE_PRIVATE);

        boolean ischeck = sp.getBoolean("autoLogin",false);
        int loginResult = sp.getInt("loginResult",0);
        if(!ischeck && loginResult != 1){
            startActivity(new Intent(getApplicationContext(),
                    LoginActivity.class));
        }



    }
}
