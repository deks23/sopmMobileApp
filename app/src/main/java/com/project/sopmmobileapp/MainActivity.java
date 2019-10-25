package com.project.sopmmobileapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;
import android.os.StrictMode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.project.sopmmobileapp.view.fragments.LoginFragment;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getName();
    private static final String CURRENT_FRAGMENT_TAG = "currentFragment";

    private Fragment currentFragment;

    private LocationManager locationManager;
    private LocationListener listener;
    private double longitude = 0.0;
    private double latitude = 0.0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            currentFragment = getSupportFragmentManager().getFragment(savedInstanceState, CURRENT_FRAGMENT_TAG);
        } else {
            this.currentFragment = new LoginFragment();
            this.changeFragment(this.currentFragment);
        }
        this.setSdkPolicy();
    }

    @Override
    protected void onSaveInstanceState(@NotNull Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, CURRENT_FRAGMENT_TAG, currentFragment);
    }

    private void setSdkPolicy() {
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onBackPressed() {
        if (this.canBack()) {
            super.getSupportFragmentManager().popBackStackImmediate();
        }
    }

    private boolean canBack() {
        return super.getSupportFragmentManager().getBackStackEntryCount() > 1;
    }

    public void changeFragment(final Fragment fragment) {
        super.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_on_fragments, fragment)
                .addToBackStack(currentFragment.getClass().toString())
                .commit();

        this.currentFragment = fragment;
    }

    public void setBaseForBackStack(final Fragment fragment) {
        this.clearBackStack();

        super.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_on_fragments, fragment)
                .addToBackStack(currentFragment.getClass().toString())
                .commit();

        this.currentFragment = fragment;
    }

    private void clearBackStack() {
        while (super.getSupportFragmentManager().popBackStackImmediate()) ;
    }

    public void putFragment(final Fragment fragment){
        super.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_on_fragments,fragment)
                .attach(fragment)
                .commit();
        this.currentFragment = fragment;

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                longitude = location.getLongitude();
                latitude = location.getLatitude();
                Toast.makeText(MainActivity.this, " " + longitude,
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) { }

            @Override
            public void onProviderEnabled(String s) { }

            @Override
            public void onProviderDisabled(String s) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };

        localization();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == 10) {
            localization();
        }
    }

    void localization(){
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.INTERNET} ,10);
            }
            return;
        }
        locationManager.requestLocationUpdates("gps", 5000, 0,
                listener);
    }
}
