package com.example.arthas.androidalarm;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.TimeZone;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.arthas.androidalarm.CreateRepeatingAlarm.EXTRA_LOCATION;
import static com.example.arthas.androidalarm.CreateRepeatingAlarm.EXTRA_MESSAGE;

public class CreateAlarm extends AppCompatActivity {


    Button start_timer;
    Button home_button;

    EditText edit_hour;
    EditText edit_minute;
    EditText edit_second;

    EditText name;

    Chronometer timer;

    //Variables to be passed into a new alarm Item
    int hour;
    int minute;
    int second;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_alarm);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//keep the keyboard hidden

        name = findViewById(R.id.message_timer);

        edit_hour = findViewById(R.id.edit_hours);
        edit_minute = findViewById(R.id.edit_minutes);
        edit_second = findViewById(R.id.edit_seconds);

        //the create button should exit this Activity and create a new alarm.
        start_timer = findViewById(R.id.start_timer_btn);


        start_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hour = Integer.parseInt(edit_hour.getText().toString());
                minute = Integer.parseInt(edit_minute.getText().toString());
                second = Integer.parseInt(edit_second.getText().toString());

                Log.i("Timer", hour + ":" + minute + ":" + second);

                long milliseconds = hour * 3600 * 1000 + minute * 60 * 1000 + second * 1000;

                Log.i("Timer", milliseconds + "");

                AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                Intent alarmIntent;
                PendingIntent pendingIntent;

                alarmIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
                alarmIntent.putExtra(EXTRA_MESSAGE, name.getText().toString());
                alarmIntent.putExtra(EXTRA_LOCATION, "004444, 123123");
                final int _id = (int) System.currentTimeMillis();
                pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), _id, alarmIntent, 0);

                Log.i("What", "are you working");
                manager.setExact(AlarmManager.RTC_WAKEUP
                        , System.currentTimeMillis()+ milliseconds
                        , pendingIntent);
                Toast.makeText(getApplicationContext(), "Timer Has Been Started", Toast.LENGTH_LONG).show();


             /*
                the chronomenter is causing it to break. So I'm giving up on that
                timer.setCountDown(true);
                timer.setBase(hour * 3600 *1000 + minute * 60 * 1000 + second * 1000 );
                timer.start();*/


            }
        });
        home_button = findViewById(R.id.back_btn);
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });
    }

}
