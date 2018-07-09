package com.test.android02layout;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SelectOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_one);



        Intent intent = getIntent();
        String row = intent.getStringExtra("row");
        //5:admin3:hi1234563:kim3:0103
        String[] temp = row.split(":");

        final SQLiteDatabase mDatabase =
                openOrCreateDatabase("member.db",
                        (int)SQLiteDatabase.CREATE_IF_NECESSARY,
                        null);
        Cursor c = mDatabase.query("member",
                null,
                "num=?",new String[]{temp[0]},
                null,
                null,
                null);
        int num = 0;
        String id = null;
        String pw = null;
        String name = null;
        String tel = null;

        c.moveToFirst();
        while(!c.isAfterLast()){
            num = c.getInt(c.getColumnIndex("num"));
            id = c.getString(c.getColumnIndex("id"));
            pw = c.getString(c.getColumnIndex("pw"));
            name = c.getString(c.getColumnIndex("name"));
            tel = c.getString(c.getColumnIndex("tel"));
            c.moveToNext();
        }



        final TextView tv_num = findViewById(R.id.tv_num);
        final EditText et_id = findViewById(R.id.et_id);
        final EditText et_pw = findViewById(R.id.et_pw);
        final EditText et_name = findViewById(R.id.et_name);
        final EditText et_tel = findViewById(R.id.et_tel);
        tv_num.setText(String.valueOf(num));
        et_id.setText(id);
        et_pw.setText(pw);
        et_name.setText(name);
        et_tel.setText(tel);


        Button btn_updateOK = findViewById(R.id.btn_updateOK);
        btn_updateOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("testLog","btn_updateOK");
                Log.i("testLog",tv_num.getText().toString());
                Log.i("testLog",et_id.getText().toString());
                Log.i("testLog",et_pw.getText().toString());
                Log.i("testLog",et_name.getText().toString());
                Log.i("testLog",et_tel.getText().toString());

                ContentValues values = new ContentValues();
                values.put("id",et_id.getText().toString());
                values.put("pw",et_pw.getText().toString());
                values.put("name",et_name.getText().toString());
                values.put("tel",et_tel.getText().toString());
                int resultCount = mDatabase.update(
                        "member",
                        values,
                        "num=?",
                        new String[]{tv_num.getText().toString()});
                Log.i("testLog","resultCount:"+resultCount);
                Intent intent = new Intent(
                        getApplicationContext(),
                        SelectAllActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        Button btn_deleteOK = findViewById(R.id.btn_deleteOK);
        btn_deleteOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("testLog","btn_deleteOK");
                Log.i("testLog",tv_num.getText().toString());

                int resultCount = mDatabase.delete("member","num=?",new String[]{tv_num.getText().toString()});
                Log.i("testLog","resultCount:"+resultCount);

                Intent intent = new Intent(
                        getApplicationContext(),
                        SelectAllActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }
}
