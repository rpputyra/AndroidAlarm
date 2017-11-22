package com.example.arthas.androidalarm;

/**
 * Created by Rob on 11/21/2017.
 */

import java.time.LocalDateTime;


/**
 The alarm will compare values between current* and set*. The values of current* will be set by
 the system time, and will be compared to the set time.
 If the current time matches the set time, the alarm will go off
 **/
public class specificAlarm extends Alarm {

    int currentYear, currentMonth, currentDay, currentHour, currentMinute, currentSecond;
//    int alarmYear, alarmMonth, alarmDay, alarmHour, alarmMinute, alarmSecond;

    LocalDateTime timepoint = LocalDateTime.now();


    public specificAlarm(){
        isSpecificAlarm = true;
    }

    public void setAlarm (int year, int month, int day, int hour, int minute, int second) throws Exception {
        this.alarmYear = year;
        if (month > 12) {
            throw new IndexOutOfBoundsException("The number months exceeds the limit");
        }else {
            this.alarmMonth = month;
        }
        if (day > 31) {
            throw new IndexOutOfBoundsException("The number days exceeds the limit");
        }else {
            this.alarmDay = day;
        }
        if (hour > 24){
            throw new IndexOutOfBoundsException("The number hours exceeds the limit");
        }else {
            this.alarmHour = hour;
        }
        if (minute > 60){
            throw new IndexOutOfBoundsException("The number minutes exceeds the limit");
        }else {
            this.alarmMinute = minute;
        }
        if (second > 60){
            throw new IndexOutOfBoundsException("The number second exceeds the limit");
        }else {
            this.alarmSecond = second;
        }

        if (!validTimer()){
            throw new Exception();
        }
    }

    public void currentDate () {
        timepoint.getYear();
        timepoint.getYear();
        timepoint.getMonthValue();
        timepoint.getDayOfMonth();
        timepoint.getHour();
        timepoint.getMinute();
        timepoint.getSecond();
    }

    public int getCurrentYear(){
        int year = timepoint.getYear();
        currentYear = year;
        return currentYear;
    }

    public int getCurrentMonth(){
        int month = timepoint.getMonthValue();
        return month;
    }

    public int getCurrentDay(){
        int day = timepoint.getDayOfMonth();
        return day;
    }

    public int getCurrentHour(){
        int hour = timepoint.getHour();
        return hour;
    }

    public int getCurrentMinute(){
        int minute = timepoint.getMinute();
        return minute;
    }

    public int getCurrentSecond(){
        int second = timepoint.getSecond();
        return second;
    }


    //Compare system time to alarm time, and if boolean value is true, alarm should enable
    //Variable 'a' will be
    public boolean compareToTime(specificAlarm alarm, specificAlarm current){
        if (alarm.alarmYear == current.currentYear && alarm.alarmMonth == current.currentMonth
                && alarm.alarmDay == current.currentDay && alarm.alarmHour == current.currentHour
                && alarm.alarmMinute == current.currentMinute
                && alarm.alarmSecond == current.currentSecond){
            return true;
        }
        return false;
    }
}

