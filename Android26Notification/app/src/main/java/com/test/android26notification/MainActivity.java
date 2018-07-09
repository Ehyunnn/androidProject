package com.test.android26notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    NotificationManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager =
                (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

    }

    public void testClick(View v){
        Log.i("testLog", "testClick().....");

        if(v.getId()==R.id.button){
            Log.i("testLog", "testClick().....button");
            String title = "제목";
            String cotent = "내용입니다.";
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel("yangssem",
                        "NAME_yangssem",
                        NotificationManager.IMPORTANCE_DEFAULT);
                channel.setDescription("DISCRIPTION_yangssem");
                manager.createNotificationChannel(channel);
            }
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(
                            getApplicationContext(),
                            "yangssem");
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);

            mBuilder.setLargeIcon(bitmap);
            mBuilder.setSmallIcon(android.R.drawable.ic_input_delete);
            mBuilder.setContentTitle(title);
            mBuilder.setContentText(cotent);

            Uri sound = RingtoneManager.getActualDefaultRingtoneUri(
                    getApplicationContext(),
                    RingtoneManager.TYPE_NOTIFICATION);
            mBuilder.setSound(sound);

            long[] vibrates = new long[]{0,3000,100,100,200,200};
            mBuilder.setVibrate(vibrates);

            Intent intent = new Intent(getApplicationContext(),DialogViewPage.class);
            intent.putExtra("title",title);
            intent.putExtra("content",cotent);

            TaskStackBuilder tsBuilder = TaskStackBuilder.create(this);
            tsBuilder.addParentStack(getClass());
            tsBuilder.addNextIntent(intent);

            PendingIntent pIntent = tsBuilder.getPendingIntent(
                    0,PendingIntent.FLAG_UPDATE_CURRENT
            );

            mBuilder.setContentIntent(pIntent);
            mBuilder.setAutoCancel(true);


            manager.notify(1, mBuilder.build());
        }else if(v.getId()==R.id.button2){
            Log.i("testLog", "testClick().....button2");
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel("default",
                        "YOUR_CHANNEL_NAME",
                        NotificationManager.IMPORTANCE_DEFAULT);
                channel.setDescription("YOUR_NOTIFICATION_CHANNEL_DISCRIPTION");
                manager.createNotificationChannel(channel);
            }
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(
                            getApplicationContext(),
                            "default")
                    .setSmallIcon(R.mipmap.ic_launcher) // notification icon
                    .setContentTitle("title") // title for notification
                    .setContentText("content")// message for notification
                    .setSound(Settings.System.DEFAULT_NOTIFICATION_URI) // set alarm sound for notification
                    .setAutoCancel(true); // clear notification after click
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            PendingIntent pi =
                    PendingIntent.getActivity(this,
                            0,
                            intent,
                            PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(pi);
            manager.notify(0, mBuilder.build());
        }
    }



}
