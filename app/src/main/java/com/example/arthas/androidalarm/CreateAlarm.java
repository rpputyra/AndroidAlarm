package com.example.arthas.androidalarm;

import android.annotation.SuppressLint;
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

public class CreateAlarm extends AppCompatActivity {


    Button start_timer;
    Button home_button;
    Chronometer timer;

    //Variables to be passed into a new alarm Item
    int hour;
    int minute;
    int day;
    int month;
    int year;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_alarm);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//keep the keyboard hidden

        timer = findViewById(R.id.chronometer);


        //the create button should exit this Activity and create a new alarm.
        start_timer = findViewById(R.id.start_timer_btn);


        start_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timer.start();

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
