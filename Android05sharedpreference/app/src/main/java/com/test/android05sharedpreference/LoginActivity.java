package com.test.android05sharedpreference;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    EditText editText;
    CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final SharedPreferences sp =
                getSharedPreferences("test",MODE_PRIVATE);
        String name = sp.getString("name","kim");
        boolean ischeck = sp.getBoolean("autoLogin",false);
        Log.i("testLog",name);
        Log.i("testLog",String.valueOf(ischeck));
        editText = findViewById(R.id.editText);
        checkBox = findViewById(R.id.checkBox);
        editText.setText(name);
        checkBox.setChecked(ischeck);

        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //id,pw check successed......
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("loginResult",1);
                editor.commit();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences sp =
                getSharedPreferences("test",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name",editText.getText().toString());
        editor.putBoolean("autoLogin",checkBox.isChecked());
        editor.commit();
    }
}
