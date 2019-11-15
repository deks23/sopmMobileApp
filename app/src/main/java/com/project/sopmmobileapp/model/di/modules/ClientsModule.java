package com.project.sopmmobileapp.model.di.modules;

import com.project.sopmmobileapp.model.di.clients.GpsClient;
import com.project.sopmmobileapp.model.di.clients.LoginClient;
import com.project.sopmmobileapp.model.di.clients.RegisterClient;
import com.project.sopmmobileapp.model.di.clients.SurveyClient;
import com.project.sopmmobileapp.model.di.clients.UserDetailsClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ClientsModule {

    @Singleton
    @Provides
    public LoginClient loginClient() {
        return new LoginClient();
    }

    @Singleton
    @Provides
    public RegisterClient registerClient() {
        return new RegisterClient();
    }

    @Singleton
    @Provides
    public UserDetailsClient userDetailsClient() {
        return new UserDetailsClient();
    }

    @Singleton
    @Provides
    public GpsClient gpsClient() {
        return new GpsClient();
    }

    @Singleton
    @Provides
    public SurveyClient surveyClient() {
        return new SurveyClient();
    }


}
