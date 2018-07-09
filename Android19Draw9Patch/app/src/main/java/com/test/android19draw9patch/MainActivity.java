package com.test.android19draw9patch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Toast t = new Toast(this);
////        t.setGravity(Gravity.TOP, 0, 0);
////        t.setDuration(Toast.LENGTH_LONG);
////        TextView tv = new TextView(this);
////        tv.setTextSize(20.0f);
////        tv.setText("Large Textaaaaaaaaaaaa\naaaaaa\naaaaaa");
////        tv.setBackgroundResource(
////                R.drawable.img02);
////        t.setView(tv);
////        t.show();

        Toast t2 = new Toast(this);
        t2.setGravity(Gravity.BOTTOM | Gravity.RIGHT, 0, 0);
        t2.setDuration(Toast.LENGTH_LONG);
        TextView tv2 = new TextView(this);
        tv2.setTextSize(20.0f);
        tv2.setText("Large Textaaaaaaaaaaaa\naaaaaa\naaaaaa");
        tv2.setBackgroundResource(
                R.drawable.img02_9);
        t2.setView(tv2);
        t2.show();


    }



}
