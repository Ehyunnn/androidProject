package com.test.android21soundpool;

import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private SoundPool soundPool;
    int soundID = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        soundPool = new SoundPool(1,
                AudioManager.STREAM_MUSIC,0);
        soundID = soundPool.load(this,
                R.raw.camera_click,
                1);//재생우선순위 0,1
        button = new Button(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(soundID,1,1,0,0,1);
            }
        });
        setContentView(button);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(soundPool!=null){
            soundPool.stop(soundID);
            soundPool.release();
            soundPool = null;
        }
    }
}
