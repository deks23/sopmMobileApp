package com.project.sopmmobileapp.model.di.clients;

import com.project.sopmmobileapp.applications.VoteApplication;
import com.project.sopmmobileapp.model.daos.LoginDao;
import com.project.sopmmobileapp.model.dtos.Credentials;
import com.project.sopmmobileapp.model.dtos.LoginResponse;
import com.project.sopmmobileapp.model.exceptions.BadRequestException;
import com.project.sopmmobileapp.model.exceptions.LoginException;

import java.net.HttpURLConnection;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import retrofit2.Retrofit;

import static io.reactivex.Single.error;
import static io.reactivex.Single.just;

public class LoginClient extends BaseClient {

    @Named("no_auth")
    @Inject
    Retrofit retrofit;

    private LoginDao loginDao;

    public LoginClient() {
        VoteApplication.getRetrofitComponent().inject(this);
        this.loginDao = retrofit.create(LoginDao.class);
    }

    public Single<LoginResponse> login(final Credentials credentials) {
        return async(this.loginDao.login(credentials)
                .flatMap(authenticationResponse -> {
                    if (authenticationResponse.isSuccessful()) {
                        return just(authenticationResponse.body());
                    }
                    if(authenticationResponse.code() == HttpURLConnection.HTTP_UNAUTHORIZED){
                        return error((new LoginException()));
                    }
                    if(authenticationResponse.code() == HttpURLConnection.HTTP_NOT_FOUND){
                        return  error(new BadRequestException());
                    }
                    return error(new RuntimeException(authenticationResponse.errorBody().toString()));
                }));
    }
}
