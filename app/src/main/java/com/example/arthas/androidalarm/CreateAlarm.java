package com.example.arthas.androidalarm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class CreateAlarm extends AppCompatActivity {

    TimePicker time_picker;
    DatePicker date_picker;
    EditText alarm_time;
    EditText alarm_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_alarm);

        time_picker = findViewById(R.id.time_picker);
        time_picker.setVisibility(View.GONE);


        date_picker = findViewById(R.id.date_picker);
        date_picker.setVisibility(View.GONE);

        //time button, on click should open the time picker
        alarm_date = findViewById(R.id.alarm_date);
        alarm_date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    Toast.makeText(getApplicationContext(), "CreateAlarm line 37. Focus Change to Date", Toast.LENGTH_LONG).show();
                    date_picker.bringToFront();
                    date_picker.setVisibility(View.VISIBLE);
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                }
            }
        });

        alarm_time = findViewById(R.id.alarm_time);
        alarm_time.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if (hasFocus) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                    Toast.makeText(getApplicationContext(), "CreateAlarm line 47. Focus Change to Time.", Toast.LENGTH_LONG);
                    time_picker.bringToFront();
                    time_picker.setVisibility(View.VISIBLE);


                }
            }
        });

        Toast.makeText(getApplicationContext(), "HELP", Toast.LENGTH_SHORT);

    }

   /* @Override
    public void onFocusChange(View view, boolean b) {
        Toast.makeText(this, "Boolean: " + b, Toast.LENGTH_LONG);
        if (alarm_date.hasFocus()){
            Toast.makeText(getApplicationContext(), view.getId() + " : " + view.getTransitionName(), Toast.LENGTH_SHORT);
        }
        else if (alarm_time.hasFocus() ){
            Toast.makeText(getApplicationContext(), view.getId() + " : " + view.getTransitionName(), Toast.LENGTH_SHORT);
        }

    }*/

}
