package com.project.sopmmobileapp.model.di.clients;

import com.project.sopmmobileapp.applications.VoteApplication;
import com.project.sopmmobileapp.model.daos.LoginDao;
import com.project.sopmmobileapp.model.dtos.BaseResponse;
import com.project.sopmmobileapp.model.dtos.Credentials;

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

    public Single<BaseResponse> login(final Credentials credentails) {
        return async(this.loginDao.login(credentails)
                .flatMap(authenticationResponse -> {
                    if (authenticationResponse.isSuccessful()) {
                        return just(authenticationResponse.body());
                    }
                    return error(new RuntimeException("Ja pitole"));
                }));
    }
}
