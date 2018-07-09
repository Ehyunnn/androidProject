package com.test.android15timertask;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private TextView tv;

    private Handler handler = new Handler();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private TimerTask timerTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        tv = (TextView)findViewById(R.id.textView);
        tv.setText("3초후 이벤트 페이지로 이동합니다.");
        final long startTime = System.currentTimeMillis();

        timerTask = new TimerTask() {
            @Override
            public void run() {
                Log.i("timerTask", "timerTask.." + System.currentTimeMillis());


                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText(sdf.format(new Date()));
                        long endTime = System.currentTimeMillis();
                        Log.i("timerTask", "endTime-startTime.." + (endTime-startTime));
//                        if(endTime-startTime>=3000){
//                            startActivity(new Intent(getApplicationContext(),EventActivity.class));
//                        }
                    }
                });

            }
        };

        Timer timer = new Timer();
//        timer.schedule(timerTask,3000);
        timer.schedule(timerTask, 3000, 1000);
//        timer.schedule(timerTask, 0, 1000);


    }
}
