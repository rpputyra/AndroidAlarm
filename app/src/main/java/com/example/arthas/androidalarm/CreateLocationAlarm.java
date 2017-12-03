package com.example.arthas.androidalarm;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import android.widget.EditText;
import android.widget.Toast;

import static com.example.arthas.androidalarm.CreateRepeatingAlarm.EXTRA_LOCATION;
import static com.example.arthas.androidalarm.CreateRepeatingAlarm.EXTRA_MESSAGE;



public class CreateLocationAlarm extends AppCompatActivity {


    Button start_timer;
    EditText edit_minute;
    EditText name;

    int minute;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_location_alarm);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//keep the keyboard hidden

        name = findViewById(R.id.message_timer);

        edit_minute = findViewById(R.id.edit_minutes);

        //the create button should exit this Activity and create a new alarm.
        start_timer = findViewById(R.id.start_timer_btn);


        start_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                minute = Integer.parseInt(edit_minute.getText().toString());

                Log.i("Timer", ":" + minute);

                long milliseconds = minute * 60 * 1000;

                Log.i("Timer", milliseconds + "");

                AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                Intent alarmIntent;
                PendingIntent pendingIntent;

                alarmIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
                alarmIntent.putExtra(EXTRA_MESSAGE, name.getText().toString());
                alarmIntent.putExtra(EXTRA_LOCATION, "004444, 123123");
                final int _id = (int) System.currentTimeMillis();
                pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), _id, alarmIntent, 0);

                manager.setExact(AlarmManager.RTC_WAKEUP
                        , System.currentTimeMillis()+ milliseconds
                        , pendingIntent);
                Toast.makeText(getApplicationContext(), "Timer Has Been Started", Toast.LENGTH_LONG).show();



                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


    }

}