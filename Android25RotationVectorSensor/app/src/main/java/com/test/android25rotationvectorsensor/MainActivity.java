package com.test.android25rotationvectorsensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity
        implements SensorEventListener {

    private SensorManager sensorManager;
    TextView tv;

    private TickHandler tickHandler;
    private float[] values = new float[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        tv = (TextView)findViewById(R.id.tv);

        tickHandler = new TickHandler();
        tickHandler.sleep(0);

    }

    public class TickHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            String text =
                    "방위:" + values[0] +
                            "\n피치:" + values[1] +
                            "\n롤:" + values[2];


            tv.setText(text);
            if (tickHandler != null)
                tickHandler.sleep(200);
        }

        public void sleep(long delayMills) {
            removeMessages(0);
            sendMessageDelayed(obtainMessage(0), delayMills);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        List<Sensor> sensors =
                sensorManager.getSensorList(
                        Sensor.TYPE_ALL);
        for (Sensor s:sensors) {
            Log.i("testLog", "s.getName():"+s.getName());
            Log.i("testLog", "s.getType():"+s.getType());
            sensorManager.registerListener(
                    this,
                    sensorManager.getDefaultSensor(s.getType()),
                    SensorManager.SENSOR_DELAY_FASTEST);
        }
//        if (sensors.size() > 0) {
//            sensorManager.registerListener(
//                    this,
//                    sensors.get(0),
//                    SensorManager.SENSOR_DELAY_FASTEST);
//        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        switch (sensor.getType()) {
            case 65558:

                values[0] = event.values[0];
                values[1] = event.values[1];
                values[2] = event.values[2];

//                if(event.values[0] > 0.5f
//                       || event.values[0] < -0.5f ){
//                    Log.i("testLog", "롤:"+String.valueOf(event.values[0]));
//                }
//                if(event.values[1] > 0.5f
//                       || event.values[1] < -0.5f ){
//                    Log.i("testLog", "피치:"+String.valueOf(event.values[1]));
//                }
//                if(event.values[2] > 0.5f
//                       || event.values[2] < -0.5f ){
//                    Log.i("testLog", "방위:"+String.valueOf(event.values[2]));
//                }

                break;
            default:
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
