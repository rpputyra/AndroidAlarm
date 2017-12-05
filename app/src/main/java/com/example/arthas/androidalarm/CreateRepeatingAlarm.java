package com.example.arthas.androidalarm;

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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TimePicker;

import static com.example.arthas.androidalarm.MainActivity.EXTRA_DATA;
import static com.example.arthas.androidalarm.MainActivity.EXTRA_LOCATION;
import static com.example.arthas.androidalarm.MainActivity.EXTRA_MESSAGE;


//ToDo: Create Repeating alarm is currently a duplicate of Create Alarm It needs to be set up to create a repeating alarm
public class CreateRepeatingAlarm extends AppCompatActivity {


    TimePicker time_picker;
    DatePicker date_picker;
    Button alarm_time_date;
    Button next; //next is used for switching from time to date
    Button create_alarm;

    //The Main difference from Create Alarm is the following radio buttons
    RadioGroup repeatTime;
    RadioButton repeat_monthly;
    RadioButton repeat_yearly;
    RadioButton repeat_30;
    Switch isRecursive;

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
        setContentView(R.layout.activity_create_repeating_alarm);

//        Radio Buttons information here
        repeatTime = findViewById(R.id.repeat_time);
        repeat_monthly = findViewById(R.id.radio_monthly);
        repeat_yearly = findViewById(R.id.radio_yearly);
        repeat_30 = findViewById(R.id.radio_30second);
        isRecursive = findViewById(R.id.recursive_switch);

        //TimeZone Buttons and Variables
        final TimeZone tz;
        RadioButton central = findViewById(R.id.central_time);
        RadioButton pacific = findViewById(R.id.pacific_time);
        RadioButton eastern = findViewById(R.id.eastern_time);
        RadioButton mountain = findViewById(R.id.mountain_time);

        if (central.isChecked()){
            tz = TimeZone.getTimeZone("Illinois/Chicago");
        }
        else if (pacific.isChecked()){
            tz = TimeZone.getTimeZone("America/Los_Angeles");
        }
        else if(eastern.isChecked()){
            tz = TimeZone.getTimeZone("New York/New York");
        }
        else if(mountain.isChecked()){
            tz = TimeZone.getTimeZone("Arizona/Pheonix");
        }
        else{
            tz = TimeZone.getTimeZone("Illinois/Chicago");
        }




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
            @RequiresApi(api = Build.VERSION_CODES.M)
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
        create_alarm = findViewById(R.id.start_timer_btn);
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

                //This if Else checks which Radio button is checked.
                //I'd imagine you can send whichever one is active back through the intent
                if(isRecursive.isChecked()) {
                    if (repeat_yearly.isChecked()) {
                        Log.i("Active", "Will Repeat Yearly");
                    } else if (repeat_monthly.isChecked()) {
                        Log.i("Active", "Will Repeat Montyly");
                    } else if (repeat_30.isChecked()) {
                        Log.i("Active", "Will Repeat Every 30 Seconds");
                    }
                }
                SpecificAlarm alarm = new SpecificAlarm();
                alarm.setAlarmName(name);
                try {
                    alarm.setAlarm(year, month, day, hour, minute, 0, tz);
                } catch (Exception e) {
                    Log.d("Error Creating Alarm", "onClick of Create Alarm Button");
                }

                MainActivity.alarmArrayList.add(alarm);

                AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                Intent alarmIntent;
                PendingIntent pendingIntent;

                alarmIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
                alarmIntent.putExtra(EXTRA_MESSAGE, name);
                alarmIntent.putExtra(EXTRA_LOCATION, "004444, 123123");
                alarmIntent.putExtra(EXTRA_DATA, "false");
                final int _id = (int) System.currentTimeMillis();
                pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), _id, alarmIntent, 0);


                if(isRecursive.isChecked()) {
                int interval = 6000;

                    if (repeat_yearly.isChecked())
                    {
                        interval = (1000*60*60*24*365);
                    }
                    else if(repeat_monthly.isChecked()){
                        interval = (1000*60*60*24*30);
                    }
                    else if(repeat_30.isChecked()){
                        interval = 6000;
                    }
                    manager.setRepeating(AlarmManager.RTC_WAKEUP
                            , alarm.timepoint.getTimeInMillis()
                            , interval
                            , pendingIntent);
                    System.out.println("Alarm created");
                }
                else{
                    manager.setExact(AlarmManager.RTC_WAKEUP
                            , alarm.timepoint.getTimeInMillis()
                            , pendingIntent);
                    System.out.println("Alarm created");
                }
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });
    }

}
