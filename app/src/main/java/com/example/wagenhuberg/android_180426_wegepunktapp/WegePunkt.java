package com.example.wagenhuberg.android_180426_wegepunktapp;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wagenhuberg on 26.04.2018.
 */

public class WegePunkt {

    private Date timestamp;
    private double longitude;
    private double altitude;


    public WegePunkt() {

    }


    public WegePunkt(Date timestamp, double longitude, double altitude) {
        this.timestamp = timestamp;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    @Override
    public String toString() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        simpleDateFormat.format(timestamp);

        return
                "Timestamp: " + timestamp +
                ", Longitude: " + longitude +
                ", Atitude:" + altitude +
                '}';
    }
}
