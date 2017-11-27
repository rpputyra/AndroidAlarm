package com.example.arthas.androidalarm;


import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;


/**
 * The alarm will compare values between current* and set*. The values of current* will be set by
 * the system time, and will be compared to the set time.
 * If the current time matches the set time, the alarm will go off
 **/
public class SpecificAlarm extends Alarm {


    int currentYear,currentMonth,currentDay,currentHour,currentMinute,currentSecond;
//    int alarmYear, alarmMonth, alarmDay, alarmHour, alarmMinute, alarmSecond;

    //According to StackOverflow We should be able to say Time time = new TIme(); followed by time.setToNow();
    //however Android studio is having none of it
    Calendar timepoint = Calendar.getInstance();

    public SpecificAlarm() {

        isSpecificAlarm = true;

    }

    public void setAlarm(int year, int month, int day, int hour, int minute, int second, TimeZone tz) throws Exception {


        timepoint.set(Calendar.YEAR, year);
        timepoint.set(Calendar.MONTH, month);
        timepoint.set(Calendar.DAY_OF_MONTH, day -1);//it was always ahead a day.
        timepoint.set(Calendar.HOUR, hour);
        timepoint.set(Calendar.MINUTE, minute);
        timepoint.set(Calendar.SECOND, second);
        timepoint.setTimeZone(tz);

    }




    @Override
    public String toString(){

        SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm a");
        return alarmName + ": " + format.format(timepoint.getTime());
    }
}


