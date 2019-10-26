package com.project.sopmmobileapp.model.di.components;

import com.project.sopmmobileapp.model.di.modules.ClientsModule;
import com.project.sopmmobileapp.view.fragments.LoginFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ClientsModule.class})
public interface ClientsComponent {

    void inject(LoginFragment loginFragment);
}
