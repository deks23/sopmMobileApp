package com.project.sopmmobileapp.model.di.components;

import com.project.sopmmobileapp.model.di.clients.LoginClient;
import com.project.sopmmobileapp.model.di.clients.RegisterClient;
import com.project.sopmmobileapp.model.di.clients.SurveyClient;
import com.project.sopmmobileapp.model.di.clients.UserDetailsClient;
import com.project.sopmmobileapp.model.di.modules.RetrofitModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RetrofitModule.class})
public interface RetrofitComponent {

    void inject(LoginClient loginClient);

    void inject(RegisterClient registerClient);

    void inject(UserDetailsClient userDetailsClient);

    void inject(SurveyClient surveyClient);
}
