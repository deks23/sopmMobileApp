package com.project.sopmmobileapp.model.di.components;

import com.project.sopmmobileapp.model.di.clients.GpsClient;
import com.project.sopmmobileapp.model.di.modules.ClientsModule;
import com.project.sopmmobileapp.view.adapters.AbstractAdapterSurveyLIstItem;
import com.project.sopmmobileapp.view.fragments.LoginFragment;
import com.project.sopmmobileapp.view.fragments.RegisterFragment;
import com.project.sopmmobileapp.view.fragments.UserDetailsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ClientsModule.class})
public interface ClientsComponent {

    void inject(LoginFragment loginFragment);

    void inject(RegisterFragment registerFragment);

    void inject(UserDetailsFragment userDetailsFragment);

    void inject(GpsClient gpsClient);

    void inject(AbstractAdapterSurveyLIstItem abstractAdapterSurveyListItem);
}
