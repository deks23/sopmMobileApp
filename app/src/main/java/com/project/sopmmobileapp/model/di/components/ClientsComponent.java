package com.project.sopmmobileapp.model.di.components;

import com.project.sopmmobileapp.model.di.clients.GpsClient;
import com.project.sopmmobileapp.model.di.modules.ClientsModule;
import com.project.sopmmobileapp.model.service.LocationService;
import com.project.sopmmobileapp.view.adapters.AdapterAllSurveysListItem;
import com.project.sopmmobileapp.view.adapters.AdapterInAreaSurveysListItem;
import com.project.sopmmobileapp.view.adapters.AdapterMySurveyListItem;
import com.project.sopmmobileapp.view.fragments.CreateSurveyFragment;
import com.project.sopmmobileapp.view.fragments.LoginFragment;
import com.project.sopmmobileapp.view.fragments.RegisterFragment;
import com.project.sopmmobileapp.view.fragments.UserDetailsFragment;
import com.project.sopmmobileapp.view.fragments.VoteFragment;
import com.project.sopmmobileapp.view.fragments.pager.AllSurveysFragment;
import com.project.sopmmobileapp.view.fragments.pager.SurveysInAreaFragment;
import com.project.sopmmobileapp.view.holders.HolderClickOption;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ClientsModule.class})
public interface ClientsComponent {

    void inject(LoginFragment loginFragment);

    void inject(RegisterFragment registerFragment);

    void inject(UserDetailsFragment userDetailsFragment);

    void inject(GpsClient gpsClient);

    void inject(CreateSurveyFragment createSurveyFragment);

    void inject(AdapterMySurveyListItem adapterMySurveyListItem);

    void inject(AdapterAllSurveysListItem adapterAllSurveysListItem);

    void inject(AllSurveysFragment allSurveysFragment);

    void inject(VoteFragment voteFragment);

    void inject(LocationService locationService);

    void inject(HolderClickOption holderClickOption);

    void inject(AdapterInAreaSurveysListItem adapterInAreaSurveysListItem);
}
