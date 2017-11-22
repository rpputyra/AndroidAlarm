package com.example.arthas.androidalarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class CreateAlarm extends AppCompatActivity {

    TimePicker time_picker;
    DatePicker date_picker;
    Button alarm_time_date;
    Button next; //next is used for switching from time to date
    Button create_alarm;

    //Variables to be passed into a new alarm Item
    String name;
    int hour;
    int minute;
    int day;
    int month;
    int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_alarm);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//keep the keyboard hidden

        //The following is setting up buttons, and making the time and date picker show up.
        time_picker = findViewById(R.id.time_picker);
        time_picker.setVisibility(View.GONE);
        date_picker = findViewById(R.id.date_picker);
        date_picker.setVisibility(View.GONE);

        final EditText alarm_name = findViewById(R.id.alarm_name);

        //set up the button for time and date, I have the onclick listeners separate because it was faster at the time
        alarm_time_date = findViewById(R.id.alarm_time_date);
        alarm_time_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //the getWindow is to prevent the keyboard from popping up at bad times
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                //I set all the buttons to invisible and the time picker and next button to visible so that it looks cleaner and is more functional
                alarm_time_date.setVisibility(View.GONE);
                time_picker.bringToFront();
                date_picker.setVisibility(View.GONE);
                time_picker.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            }
        });

        //next button will only show up when the Time Picker is open its purpose is to change from the time picker to the date picker.
        next = findViewById(R.id.time_okay);
        next.setVisibility(View.GONE);
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //again the keyboard was annoying me so I just put this everywhere
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                time_picker.bringToFront();
                date_picker.setVisibility(View.VISIBLE);
                time_picker.setVisibility(View.GONE);
                next.setVisibility(View.GONE);
                create_alarm.setVisibility(View.VISIBLE);
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

                hour = time_picker.getHour();
                minute = time_picker.getMinute();

                Log.i("Time:Date",  hour + ":" + minute);
            }
        });


        //the create button should exit this Activity and create a new alarm.
        create_alarm = findViewById(R.id.create_alarm);
        create_alarm.setVisibility(View.GONE);

        create_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = alarm_name.getText().toString();
                day = date_picker.getDayOfMonth();
                month = date_picker.getMonth();
                year = date_picker.getYear();
                Log.i("Time:Date", month + "/" + day + "/" + year);
                Log.i("Time:Date", name );
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
