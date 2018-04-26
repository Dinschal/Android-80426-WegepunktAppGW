package com.example.wagenhuberg.android_180426_wegepunktapp;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.security.Permission;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Button btnWPSave;
    private Button btnWPGet;
    private LocationManager locationManager;
    private boolean isGpsEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isGpsEnabled = false;
        btnWPSave = findViewById(R.id.buttonSAVE);
        btnWPSave.setEnabled(false);
        btnWPGet = findViewById(R.id.buttonGet);
        btnWPGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "Berechtigung nicht erteilt", Toast.LENGTH_SHORT).show();
                }

                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);


                WegePunkt wegePunkt = new WegePunkt(new Date(), location.getLongitude(), location.getAltitude());
                Log.d("Meins", wegePunkt.toString());

            }
        });


    }

    //Anfordern Berechtigungen für GPS
    private void requestPermission() {
        String[] persmissions = new String[1];
        persmissions[0] = Manifest.permission.ACCESS_FINE_LOCATION;
        requestPermissions(persmissions, 4711);

    }


    private void initLocationManager() {
       //this für diese Activity
        //context für im Hintergrund - nochmals nachlesen!!!
        locationManager = (LocationManager) this.getSystemService(this.LOCATION_SERVICE);
        isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }



    @Override
    protected void onStart() {
        super.onStart();
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(this, "Bitte Berechtigung erteilen!", Toast.LENGTH_LONG).show();
            }
            requestPermission();

        } else {
            activateSaveLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 4711 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            activateSaveLocation();
        }
    }

    private void activateSaveLocation() {
        this.initLocationManager();
        btnWPSave.setEnabled(this.isGpsEnabled);

    }

}

