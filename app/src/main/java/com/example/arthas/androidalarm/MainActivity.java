package com.example.arthas.androidalarm;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FusedLocationProviderClient mFusedLocationClient;

    //an array list of alarms to fill the list view
    public static ArrayList<Alarm> alarmArrayList = new ArrayList<>();
    private ListView alarm_list;
    ArrayAdapter<Alarm> arrayAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //These buttons are for switching activities.
        Button new_alarm_btn = (Button) findViewById(R.id.new_alarm_btn);
        new_alarm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //vain attempts to prevent the alarm list view from containing duplicates
                arrayAdapter = null;
                alarm_list.setAdapter(null);

                //change activities
                Intent intent = new Intent(getApplicationContext(), CreateAlarm.class);
                startActivity(intent);
            }
        });

        Button new_timer_btn = findViewById(R.id.new_repeating_btn);
        new_timer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //vain attempts to prevent the alarm list view from containing duplicates
                arrayAdapter = null;
                alarm_list.setAdapter(null);

                //change activities
                Intent intent = new Intent(getApplicationContext(), CreateRepeatingAlarm.class);
                startActivity(intent);
            }
        });

        Button new_location_btn = findViewById(R.id.new_location_btn);
        new_location_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Need to make new Activity for this", Toast.LENGTH_LONG);
            }
        });

        /*
        The following is setting up the List View Using an array List Adapter
        TODO: This is using an array list of alarms to set up the list view which seems to be wroking.
         */
        alarm_list = findViewById(R.id.alarm_listview);
/*
        //TEST ALARM
        SpecificAlarm alarm = new SpecificAlarm();
        alarm.setAlarmName("New Years Alarm");
        try {
            alarm.setAlarm(2018, 1, 1, 0, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("Alarm", "Alarm Breaking on Creation");
        }
        Log.i("ALARM", alarm.toString());

        alarmArrayList.add(alarm);//add the TEST alarm to the arraylist of alarms*/


        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, alarmArrayList );//TODO: this should become a list of alarms

        //set the adapter with the alarms as the adapter for the list view
        alarm_list.setAdapter(arrayAdapter);



        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        //This is pending further handling
        //-Max
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        //This method will give the last retrieved location. We are retrieving FINE_LOCATION as
        //indicated in the AndroidManifest.xml
        //-Max
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            //TODO: Logic to handle location object
                        }
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
