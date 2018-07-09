package com.test.android02layout;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        final SQLiteDatabase mDatabase = openOrCreateDatabase("member.db",
                (int)SQLiteDatabase.CREATE_IF_NECESSARY,
                null);

        final EditText et_id = findViewById(R.id.et_id);
        final EditText et_pw = findViewById(R.id.et_pw);
        final EditText et_name = findViewById(R.id.et_name);
        final EditText et_tel = findViewById(R.id.et_tel);
        Button btn_insertOK = findViewById(R.id.btn_insertOK);
        btn_insertOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("testLog","btn_insertOK");
                Log.i("testLog",et_id.getText().toString());
                Log.i("testLog",et_pw.getText().toString());
                Log.i("testLog",et_name.getText().toString());
                Log.i("testLog",et_tel.getText().toString());
                ContentValues values = new ContentValues();
                values.put("id",et_id.getText().toString());
                values.put("pw",et_pw.getText().toString());
                values.put("name",et_name.getText().toString());
                values.put("tel",et_tel.getText().toString());
                long seq = mDatabase.insert(
                        "member","",values);
                Log.i("testLog","seq:"+seq);

                startActivity(new Intent(
                        getApplicationContext(),
                        SelectAllActivity.class));
            }
        });
        Button btn_selectAll = findViewById(R.id.btn_selectAll);
        btn_selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("testLog","btn_selectAll");
                Intent intent = new Intent(
                        getApplicationContext(),
                        SelectAllActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}
