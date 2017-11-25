package com.example.arthas.androidalarm;


import android.icu.util.Calendar;

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

    public void setAlarm(int year, int month, int day, int hour, int minute, int second) throws Exception {
        this.alarmYear = year;
        if (month > 12) {
            throw new IndexOutOfBoundsException("The number months exceeds the limit");
        } else {
            this.alarmMonth = month;
        }
        if (day > 31) {
            throw new IndexOutOfBoundsException("The number days exceeds the limit");
        } else {
            this.alarmDay = day;
        }
        if (hour > 24) {
            throw new IndexOutOfBoundsException("The number hours exceeds the limit");
        } else {
            this.alarmHour = hour;
        }
        if (minute > 60) {
            throw new IndexOutOfBoundsException("The number minutes exceeds the limit");
        } else {
            this.alarmMinute = minute;
        }
        if (second > 60) {
            throw new IndexOutOfBoundsException("The number second exceeds the limit");
        } else {
            this.alarmSecond = second;
        }
/*
        if (!validTimer()){
            throw new Exception();
        }
*/
    }

    public void currentDate() {
    //    timepoint.getYear();
        getCurrentYear();
  //      timepoint.getMonthValue();
        getCurrentMonth();
//        timepoint.getDayOfMonth();
        getCurrentDay();

       // timepoint.getHour();
        getCurrentHour();
        //timepoint.getMinute();
        getCurrentMinute();
        //timepoint.getSecond();
        getCurrentSecond();
    }

    public int getCurrentYear() {
        int year = timepoint.get(Calendar.YEAR);
        currentYear = year;
        return currentYear;
    }

    public int getCurrentMonth() {
        int month = timepoint.get(Calendar.DAY_OF_MONTH);//I'm not sure if this is right but it was the closes to month I say.
        currentMonth = month;
        return month;
    }

    public int getCurrentDay() {
        int day = timepoint.get(Calendar.DAY_OF_YEAR);
        return day;
    }

    public int getCurrentHour() {
        int hour = timepoint.get(Calendar.HOUR);
        return hour;
    }

    public int getCurrentMinute() {
        int minute = timepoint.get(Calendar.MINUTE);
        return minute;
    }

    public int getCurrentSecond() {
        int second = timepoint.get(Calendar.SECOND);
        return second;
    }


    //Compare system time to alarm time, and if boolean value is true, alarm should enable
    //Variable 'a' will be
    public boolean compareToTime(SpecificAlarm alarm, SpecificAlarm current) {
        if (alarm.alarmYear == current.currentYear && alarm.alarmMonth == current.currentMonth
                && alarm.alarmDay == current.currentDay && alarm.alarmHour == current.currentHour
                && alarm.alarmMinute == current.currentMinute
                && alarm.alarmSecond == current.currentSecond) {
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return getAlarmName() + ": Date " + getDateTime() + " Time " + getAlarmTime();
    }
}


