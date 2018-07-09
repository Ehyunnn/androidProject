package com.test.android02layout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SelectAllActivity extends AppCompatActivity {

    private ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_all);

        SQLiteDatabase mDatabase =
                openOrCreateDatabase("member.db",
                        (int)SQLiteDatabase.CREATE_IF_NECESSARY,
                        null);
        Cursor c = mDatabase.query("member",
                null,
                null,null,
                null,
                null,
                "num desc");
        c.moveToFirst();
        while(!c.isAfterLast()){
            int num = c.getInt(c.getColumnIndex("num"));
            String id = c.getString(c.getColumnIndex("id"));
            String pw = c.getString(c.getColumnIndex("pw"));
            String name = c.getString(c.getColumnIndex("name"));
            String tel = c.getString(c.getColumnIndex("tel"));
            list.add(
                    num
                    +":"+id+":"+pw+":"+name+":"+tel);
            c.moveToNext();
        }


        ListView lv_list = findViewById(R.id.lv_list);
        lv_list.setAdapter(
                new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_list_item_1,
                        list));

        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, int position,
                                    long id) {
                Log.i("testLog",String.valueOf(position));
                String num = list.get(position).split(":")[0];
                String row = list.get(position);
                Log.i("testLog",num);
                Log.i("testLog",row);
                Intent intent = new Intent(
                        getApplicationContext(),
                        SelectOneActivity.class);
                intent.putExtra("row",row);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

                startActivity(intent);


            }
        });

        Button btn_insert = findViewById(R.id.btn_insert);
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

    }
}
