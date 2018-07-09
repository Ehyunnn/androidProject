package com.test.android24sensorrps;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity
implements SensorEventListener {
    private SensorManager sensorManager;
    TextView tv_rps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager =
                (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors =
                sensorManager.getSensorList(
                        Sensor.TYPE_ACCELEROMETER);
        if (sensors.size() > 0) {
            sensorManager.registerListener(
                    this,
                    sensors.get(0),
                    SensorManager.SENSOR_DELAY_NORMAL);
        }

        tv_rps = findViewById(R.id.tv_rps);
        tv_rps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isBigX = false;
            }
        });

    }//end onCreate()

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(this);
    }

    private boolean isBigX;
    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        switch (sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                if(event.values[0]>20.0f){
                    if(!isBigX){

                        Log.i("testLog",
                                "x:"+event.values[0]+","+"y:"+event.values[1]);
                        isBigX = !isBigX;
                        displayRPS();
                    }

                }
                break;
            default:
                break;
        }
    }

    Random r = new Random();
    String text_rps[] = new String[]{"가위","바위","보"};
    private void displayRPS() {
        String result = text_rps[r.nextInt(3)];
        Log.i("testLog",result);
        tv_rps.setText(result);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
