package com.example.arthas.androidalarm;

/**
 * Created by Rob on 11/21/2017.
 */

import java.time.*;

public class Timer extends Alarm{

    int currentHour, currentMinute, currentSecond, currentYear, currentMonth,currentDay;
    int year, month, day, hour, minute, second;

    public Timer(){

        currentHour = LocalTime.now().getHour();
        currentMinute = LocalTime.now().getMinute();
        currentSecond = LocalTime.now().getSecond();

        currentYear = LocalDate.now().getYear();
        currentMonth = LocalDate.now().getMonthValue();
        currentDay = LocalDate.now().getDayOfMonth();
    }
    //CHeck if this works
    public void setTimer(int year, int month, int day, int hour, int minute, int second) throws Exception {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;


        if ((currentSecond+second) >= 60) {
            int total = currentSecond+second;
            alarmSecond = total - 60;
            minute++;
        }
        else {alarmSecond = currentSecond+second;}

        if ((currentMinute + minute ) >= 60) {
            int total = currentMinute+minute;
            alarmMinute = total - 60;
            hour++;
        }
        else {alarmMinute = currentMinute + minute;}

        if((currentHour+hour)>=24) {
            int total = currentHour + hour;
            alarmHour = total - 24;
            day++;
        }
        else {alarmHour = currentHour + hour;}


        //February
        if(currentMonth == 2) {
            if((currentDay + day) > 28) {
                int total = currentDay + day;
                alarmDay = total - 28;
                month++;
            }
            else {alarmDay = currentDay + day;}
        }

        //January
        else if (currentMonth == 1) {
            if((currentDay+day) > 59) {
                int total = currentDay + day;
                alarmDay = total-59;
                month = month+2;
            }
            else {alarmDay = currentDay + day;}
        }
        else if(currentMonth == 3 ||
                currentMonth == 5 ||
                currentMonth == 7 ||
                currentMonth == 8 ||
                currentMonth == 10 ||
                currentMonth == 12) {
            if((currentDay+day) > 31) {
                int total = currentDay + day;
                alarmDay = total - 31;
                month++;
            }
            else if ((currentDay+day) > 61) {
                int total = currentDay + day;
                alarmDay = total - 31;
                month = month+2;
            }
            else {alarmDay = currentDay + day;}
        }
        else {
            if((currentDay+day) > 30) {
                int total = currentDay + day;
                alarmDay = total - 30;
                month++;
            }
            else {alarmDay = currentDay + day;}
        }

        if(currentMonth+month >12) {
            int total = currentMonth + month;
            alarmMonth = total - 12;
            year++;
        }
        else {alarmMonth = currentMonth + month;}

        alarmYear = currentYear + year;

        if(!validTimer()) {
            throw new Exception();
        }
    }

}
