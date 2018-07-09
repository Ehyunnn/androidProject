package com.test.android23sensor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import java.util.List;


public class MainActivity extends AppCompatActivity
        implements SensorEventListener {

    private SoundPool soundPool;
    int soundID;

    private SensorManager sensorManager;
    private SensorViewEx sensorView;
    private int winWidth;
    private int winHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sensorView = new SensorViewEx(this);
        setContentView(sensorView);

        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        sensorManager =
                (SensorManager) getSystemService(SENSOR_SERVICE);

        WindowManager wm =
                (WindowManager) getSystemService(WINDOW_SERVICE);
        Display mDisplay = wm.getDefaultDisplay();
        winWidth = mDisplay.getWidth();
        winHeight = mDisplay.getHeight();


        List<Sensor> sensors =
                sensorManager.getSensorList(
                        Sensor.TYPE_ACCELEROMETER);
        if (sensors.size() > 0) {
            sensorManager.registerListener(
                    this,
                    sensors.get(0),
                    SensorManager.SENSOR_DELAY_FASTEST);
        }

        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        soundID = soundPool.load(this,
                R.raw.camera_click,
                1);//재생우선순위 0,1
    }// end onCrate()


    class SensorViewEx extends SurfaceView
            implements SurfaceHolder.Callback {

        private static final int LAMP_SIZE = 50;
        private Bitmap lamp;
        private int w;
        private int h;
        private float x;
        private float y;
        private int count = 0;
        private long lasttime =
                System.currentTimeMillis();
        private CustomViewThread customViewThread;
        private String str = "화면을터치하세요.";


        public SensorViewEx(Context c) {
            super(c);
            lamp = BitmapFactory.decodeResource(
                    c.getResources(),
                    R.mipmap.ic_launcher);
            getHolder().addCallback(this);
            customViewThread =
                    new CustomViewThread(getHolder(), this);
            setFocusable(true);
        }// end constructor


        public void move(float mx, float my) {

            this.x -= (mx * 4f);
            this.y += (my * 4f);
            if (this.x < 35.0f) {
                this.x = 35.0f;
            } else if (this.x > winWidth - 35) {
                this.x = winWidth - 35;
            } else if ((this.x + LAMP_SIZE) > this.w) {
                this.x = this.w - LAMP_SIZE;
            }
            if (this.y < 38.0f) {
                this.y = 38.0f;
            } else if (this.y > winHeight - 40) {
                this.y = winHeight - 40;
            } else if ((this.y + LAMP_SIZE) > this.h) {
                this.y = this.h - LAMP_SIZE;
            }
            invalidate();
            // Log.i(LOG_TAG,this.x+" : "+this.y);

        }// end move

        @Override
        protected void onSizeChanged(
                int w, int h, int oldw, int oldh) {
            this.w = w;
            this.h = h;
            // this.x = (w - LAMP_SIZE) / 2f;
            // this.y = (h - LAMP_SIZE) / 2f;
        }

        @Override
        public void onDraw(Canvas canvas) {
            float screenXCenter = winWidth / 2f;
            float screenYCenter = winHeight / 2f;
            float radius = 200.0f;
            float lampXHalf = x - (lamp.getWidth() / 2);
            float lampYHalf = y - (lamp.getHeight() / 2);

            canvas.drawColor(Color.parseColor("#000000"));
            canvas.drawBitmap(lamp, lampXHalf, lampYHalf, null);

            Paint text = new Paint();
            text.setARGB(200, 255, 255, 255);
            text.setTextSize(30);
            canvas.drawText(str, 0, 30, text);

            if ((this.x > (screenXCenter - 10)
                    && this.x < (screenXCenter + 10))
                    && (this.y > (screenYCenter - 10)
                    && this.y < (screenYCenter + 10))) {
                radius = 100.0f;

                if (System.currentTimeMillis() - lasttime > 1000) {
                    count++;

                    lasttime = System.currentTimeMillis();
                }
                if (count >= 5) {
                    str = "성공";
                }else {
                    str = Integer.toString(count);
                    soundPool.play(soundID,1,1,0,0,1);
                }
            } else {
                str = "화면을터치하세요.";
                count = 0;
            }

            Paint circleLayer = new Paint();
            circleLayer.setARGB(100, 255, 255, 255);
            canvas.drawCircle(
                    screenXCenter,
                    screenYCenter,
                    radius,
                    circleLayer);

            circleLayer.setColor(Color.BLACK);
            canvas.drawLine(screenXCenter - 50,
                    screenYCenter,
                    screenXCenter + 50,
                    screenYCenter,
                    circleLayer);
            canvas.drawLine(screenXCenter,
                    screenYCenter - 50,
                    screenXCenter,
                    screenYCenter + 50,
                    circleLayer);
        }// end onDraw

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            customViewThread.setRunning(true);
            customViewThread.start();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            boolean result = true;
            customViewThread.setRunning(false);
            while (result) {
                try {
                    customViewThread.join();
                    result = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            x = event.getX();
            y = event.getY();

            Log.i("testLog", "winWidth:" + winWidth);
            Log.i("testLog", "winHeight:" + winHeight);

            // return super.onTouchEvent(event);
            return true;
        }

    }//end SensorViewEx class

    class CustomViewThread extends Thread {

        private SurfaceHolder surfaceHolder;
        private SensorViewEx customView;
        private boolean running = false;

        public void setRunning(boolean running) {
            this.running = running;
        }

        public CustomViewThread(
                SurfaceHolder surfaceHolder,
                SensorViewEx customView) {
            super();
            this.surfaceHolder = surfaceHolder;
            this.customView = customView;
        }

        @SuppressLint("WrongCall")
        @Override
        public void run() {
            Canvas canvas;
            while (running) {
                canvas = null;
                try {
                    canvas = surfaceHolder.lockCanvas(null);
                    synchronized (surfaceHolder) {
                        customView.onDraw(canvas);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (canvas != null)
                        surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }//end CustomViewThread class

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(
            Sensor sensor, int accuracy) {
    }// end onAccuracyChanged

    @Override
    public void onSensorChanged(
            final SensorEvent event) {
        Sensor sensor = event.sensor;
        switch (sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                sensorView.move(
                        event.values[0], event.values[1]);

                break;
            default:
                break;
        }
    }// end onSensorChanged

    @Override
    protected void onStop() {
        super.onStop();
        soundPool.release();
        soundPool = null;
    }
}
