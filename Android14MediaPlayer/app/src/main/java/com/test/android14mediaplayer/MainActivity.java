package com.test.android14mediaplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    private ImageButton btn_start;
    private Button btn_pause;
    private Button btn_restart;
    private Button btn_stop;

    private MediaPlayer mp;
    private int pausePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_start = findViewById(R.id.btn_start);
        btn_pause = findViewById(R.id.btn_pause);
        btn_restart = findViewById(R.id.btn_restart);
        btn_stop = findViewById(R.id.btn_stop);

        btn_start.setOnClickListener(this);
        btn_pause.setOnClickListener(this);
        btn_restart.setOnClickListener(this);
        btn_stop.setOnClickListener(this);

    }//end onCeate()

    @Override
    protected void onStop() {
        super.onStop();
        if(mp!=null){
            mp.stop();
            mp.release();
            mp = null;
        }
    }//end onStop()


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_start:
                Log.i("testLog","btn_start...");

                mp = MediaPlayer.create(getApplicationContext(),
                        R.raw.test_cbr);
                mp.seekTo(7000);
                mp.start();
                break;
            case R.id.btn_pause:
                Log.i("testLog","btn_pause...");
                pausePosition = mp.getCurrentPosition();
                mp.pause();
                break;
            case R.id.btn_restart:
                Log.i("testLog","btn_restart...");
                mp.seekTo(pausePosition);
                mp.start();
                break;
            case R.id.btn_stop:
                Log.i("testLog","btn_stop...");
                mp.stop();
                break;
            default:break;
        }

    }
}//end class
