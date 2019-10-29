package com.project.sopmmobileapp;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

public class Gps implements LocationListener {

    private Context context;
    private Location location;
    private static final int LOCATION_REFRESH_TIME = 60_000;
    private static final int LOCATION_REFRESH_DISTANCE = 1_000;

    private static final String TAG = Gps.class.getName();

    public Gps(Context context) {
        super();
        this.context = context;
        init();
    }

    private void init() {
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.e(TAG, "GPS permission not granted");
        }
        try {
            LocationManager lm = (LocationManager)
                    context.getSystemService(Context.LOCATION_SERVICE);
            boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (isGPSEnabled) {
                Log.i(TAG, "GPS enabled");
                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME, LOCATION_REFRESH_DISTANCE, this);
            } else {
                Log.w(TAG, "GPS not enabled");
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
        Toast.makeText(context.getApplicationContext(), location.getLatitude() + " " + location.getLongitude(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    public Location getLocation() {
        return location;
    }
}

