package com.project.sopmmobileapp.model.service;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.project.sopmmobileapp.applications.VoteApplication;
import com.project.sopmmobileapp.model.di.clients.GpsClient;

import java.util.Optional;

import javax.inject.Inject;

public class LocationService extends Service {

    public static LocationService instance;

    @Inject
    GpsClient gpsClient;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("not");
    }

    @Override
    public void onCreate() {
        VoteApplication.getClientsComponent().inject(this);
        instance = this;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public Optional<Location> getLocation() {
        return gpsClient.getOptionalLocation();
    }
}
