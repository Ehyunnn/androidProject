package com.test.android27service;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

public class TestService  extends Service
        implements SensorEventListener {

    private SensorManager sensorManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("testLog", "Service onCreate.....");

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);


    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i("testLog", "Service onStart.....");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("testLog", "Service onStartCommand.....");

        List<Sensor> sensors =
                sensorManager.getSensorList(
                        Sensor.TYPE_PROXIMITY);
        if (sensors.size() > 0) {
            sensorManager.registerListener(
                    this,
                    sensors.get(0),
                    SensorManager.SENSOR_DELAY_FASTEST);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("testLog", "Service onDestroy.....");
        sensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        switch (sensor.getType()) {
            case Sensor.TYPE_PROXIMITY:
                Log.i("testLog", String.valueOf(event.values[0]));

            default:
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
