package com.example.arthas.androidalarm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by Rob on 11/22/2017.
 */

public class AlarmReceiver extends BroadcastReceiver{

    NotificationManager notificationManager = null;
    @Override
    public void onReceive(Context context, Intent intent) {


        String message = intent.getStringExtra(CreateRepeatingAlarm.EXTRA_MESSAGE);
        Log.i("message", message);
        String location = intent.getStringExtra(CreateRepeatingAlarm.EXTRA_LOCATION);



        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel("ID", "Name", importance);
            notificationManager.createNotificationChannel(notificationChannel);
            builder = new NotificationCompat.Builder(context.getApplicationContext(), notificationChannel.getId());
        } else {
            builder = new NotificationCompat.Builder(context.getApplicationContext());
        }

        System.out.println("why are you not working.");
        builder = builder
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setWhen(System.currentTimeMillis())
                .setContentTitle("Location: " + MainActivity.Latitude + " , " + MainActivity.Longitude)
                .setContentText(message)
                .setDefaults(Notification.DEFAULT_ALL)
                .setAutoCancel(true)
                .setContentInfo("Info");
        System.out.println("notification created!");

        final int _id = (int) System.currentTimeMillis();

                notificationManager.notify(_id, builder.build());

        Log.i("Cancel", "Part 1");
        removeNotification(_id);



        Log.i("Cancel", "Part 4");
    }

    private void removeNotification(int id) {
        Handler handler = new Handler();
        final int notificationID = id;
        long delayInMilliseconds = 10000;
        Log.i("Cancel", "Part 2");
        handler.postDelayed(new Runnable() {
            public void run() {
                notificationManager.cancel(notificationID);
                Log.i("Cancel", "Part 3");
            }
        }, delayInMilliseconds);
    }
}
