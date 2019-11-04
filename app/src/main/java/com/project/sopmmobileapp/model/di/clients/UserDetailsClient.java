package com.project.sopmmobileapp.model.di.clients;

import com.project.sopmmobileapp.model.dtos.request.UserDetails;
import com.project.sopmmobileapp.model.dtos.response.BaseResponse;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import retrofit2.Retrofit;

public class UserDetailsClient extends BaseClient {

    @Named("auth")
    @Inject
    Retrofit retrofit;

    public UserDetailsClient() {

    }

    public Single<BaseResponse> save(final UserDetails userDetails) {
        return null;
    }

}
