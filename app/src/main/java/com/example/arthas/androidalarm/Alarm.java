package com.example.arthas.androidalarm;

/**
 * Created by Rob on 11/21/2017.
 */

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * @author I don't remember
 *
 * We still need to set up time and alarm functionalities
 *
 */
public class Alarm {

    boolean isSpecificALarm;
    double currentTime;
    String alarmName = "";
    int alarmYear, alarmMonth, alarmDay, alarmHour, alarmMinute, alarmSecond;

    int date;
    boolean playing;
    boolean snooze;
    String message ="";
    int numSnoozes;
    boolean isSpecificAlarm;


    //Audio
    String soundFile = "LegitAlarmSound.wav";
    Clip clip;


    public Alarm() {
        numSnoozes = 0;
    }


    //call when you need to play the alarm sound.
    public void playSoundFile() {

        if ( !playing ) {

            try {

                File f = new File("./" + soundFile);
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
                clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.loop( Clip.LOOP_CONTINUOUSLY );
                playing = true;

            } catch ( LineUnavailableException e ) {

                System.out.println( "Exception Caught 1: LineUnavailableException" );
                e.printStackTrace();
            } catch ( UnsupportedAudioFileException e ) {

                System.out.println( "Exception Caught 2: UnsupportedAudioFileException" );
                e.printStackTrace();
            } catch ( IOException e ) {

                System.out.println( "Exception Caught 3: IOException" );
                e.printStackTrace();

            } catch( IllegalArgumentException e){

                System.out.println(e.getMessage() + " + possibly related to hardware inadiquacies " );
            }
        }

    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getAlarmTime() {
        String alarmTime = String.valueOf(alarmHour) + ":" + String.valueOf(alarmMinute) + ":" + String.valueOf(alarmSecond);
        return alarmTime;
    }

    public String getDateTime() {
        String alarmDate =  String.valueOf(alarmMonth) + "/" + String.valueOf(alarmDay) + "/" + String.valueOf(alarmYear);
        return alarmDate;
    }

    public boolean validTimer() {

        LocalDate LocalDateCreated = LocalDate.of(alarmYear, alarmMonth, alarmDay);
        LocalDate nowDate = LocalDate.now();

        LocalTime LocalTimeCreated = LocalTime.of(alarmHour, alarmMinute, alarmSecond);
        LocalTime nowTime = LocalTime.now();

        if ((LocalDateCreated.compareTo(nowDate)) == 1) {
            return true;
        }
        else if((LocalDateCreated.compareTo(nowDate)) == 0) {
            if((LocalTimeCreated.compareTo(nowTime)) == 1) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public boolean timeToRing() {
        if (alarmYear == LocalDate.now().getYear() &&
                alarmMonth == LocalDate.now().getMonthValue() &&
                alarmDay == LocalDate.now().getDayOfMonth() &&
                alarmHour == LocalTime.now().getHour() &&
                alarmMinute == LocalTime.now().getMinute() &&
                alarmSecond == LocalTime.now().getSecond())
        {
            return true;
        }
        else {return false;}
    }


    //call to dismiss alarm sound
    //(side note I tried using clip.isActive and it threw an error if clip hadn't been instantiated yet so I'm sticking with the bool for now)
    public void dismiss() {


        if ( playing) {
            clip.stop();
            playing = false;
        }
    }

    //Getters and Setters for Alarm name, Alarm name will show up in the JFrame
    public String getAlarmName() {
        return alarmName;
    }


    public void setAlarmName(String _alarmName) {
        alarmName = _alarmName;
    }

    int getAlarmYear() {
        return alarmYear;
    }

    int getAlarmMonth() {
        return alarmMonth;
    }

    int getAlarmDay() {
        return alarmDay;
    }

    int getAlarmHour() {
        return alarmHour;
    }

    int getAlarmMinute() {
        return alarmMinute;
    }

    int getAlarmSecond() {
        return alarmSecond;
    }


    public int getNumSnoozes() {
        return numSnoozes;
    }


    public void setNumSnoozes(int numSnoozes) {
        this.numSnoozes = numSnoozes;
    }

}

