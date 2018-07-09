package com.test.android02layout;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase mDatabase =
                openOrCreateDatabase("member.db",
                (int)SQLiteDatabase.CREATE_IF_NECESSARY,
                null);
        mDatabase.execSQL("create table if not exists member(" +
                "num integer primary key autoincrement," +
                "id text,pw text,name text,tel text);");
//        mDatabase.execSQL("drop table member");


        Button btn_insert = findViewById(R.id.btn_insert);
        Button btn_selectAll = findViewById(R.id.btn_selectAll);

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("testLog","btn_insert");
                Intent intent = new Intent(
                        getApplicationContext(),
                        InsertActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
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
