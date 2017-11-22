package com.example.arthas.androidalarm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

public class CreateAlarm extends AppCompatActivity implements TimePicker.OnTimeChangedListener {

    TimePicker time_picker;
    DatePicker date_picker;
    Button alarm_time;
    Button next; //next is used for switching from time to date
    Button create_alarm;

    String time;
    String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_alarm);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        time_picker = findViewById(R.id.time_picker);
        time_picker.setVisibility(View.GONE);
        time_picker.setOnTimeChangedListener(this);


        date_picker = findViewById(R.id.date_picker);
        date_picker.setVisibility(View.GONE);

        alarm_time = findViewById(R.id.alarm_time);
        alarm_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                Toast.makeText(getApplicationContext(), "CreateAlarm line 47. Focus Change to Time.", Toast.LENGTH_LONG);
                alarm_time.setVisibility(View.GONE);
                time_picker.bringToFront();
                date_picker.setVisibility(View.GONE);
                time_picker.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            }
        });

        next = findViewById(R.id.time_okay);
        next.setVisibility(View.GONE);
        next.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                Toast.makeText(getApplicationContext(), "CreateAlarm line 47. Focus Change to Time.", Toast.LENGTH_LONG);
                time_picker.bringToFront();
                date_picker.setVisibility(View.VISIBLE);
                time_picker.setVisibility(View.GONE);
                next.setVisibility(View.GONE);
                create_alarm.setVisibility(View.VISIBLE);
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            }
        });

        Toast.makeText(getApplicationContext(), "HELP", Toast.LENGTH_SHORT);


        create_alarm = findViewById(R.id.create_alarm);
        create_alarm.setVisibility(View.GONE);
        create_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Toast.makeText(getApplicationContext(), "TO BE SET", Toast.LENGTH_LONG);
            }
        });
    }

    @Override
    public void onTimeChanged(TimePicker timePicker, int i, int i1) {
            time = timePicker.toString();
            Toast.makeText(getApplicationContext(), time + "HELLO", Toast.LENGTH_LONG);
    }
}
