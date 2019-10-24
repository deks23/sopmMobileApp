package com.project.sopmmobileapp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.project.sopmmobileapp.model.daos.LoginDao;
import com.project.sopmmobileapp.model.dtos.BaseResponse;
import com.project.sopmmobileapp.model.dtos.Credentials;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private LoginDao service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadAnswers();
    }

    public void loadAnswers() {
        Credentials credentials = new Credentials("username", "password");
        service.login(credentials).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {

                if (response.isSuccessful()) {
                    Log.d("MainActivity", "posts loaded from API");
                } else {
                    int statusCode = response.code();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.d("MainActivity", "error loading from API");

            }
        });
    }
}
