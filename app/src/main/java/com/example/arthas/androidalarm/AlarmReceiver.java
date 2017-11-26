package com.example.arthas.androidalarm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Rob on 11/22/2017.
 */

public class AlarmReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {


        String message = intent.getStringExtra(CreateRepeatingAlarm.EXTRA_MESSAGE);
        String location = intent.getStringExtra(CreateRepeatingAlarm.EXTRA_LOCATION);



        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
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
                .setContentTitle("Location: " + location)
                .setContentText(message)
                .setDefaults(Notification.DEFAULT_ALL)
                .setAutoCancel(true)
                .setContentInfo("Info");
        System.out.println("notification created!");
                notificationManager.notify(1, builder.build());
    }
}
