package com.project.sopmmobileapp.model.di.clients;

import android.util.Log;

import com.project.sopmmobileapp.applications.VoteApplication;
import com.project.sopmmobileapp.model.daos.UserDetailsDao;
import com.project.sopmmobileapp.model.request.UserDetails;
import com.project.sopmmobileapp.model.response.BaseResponse;
import com.project.sopmmobileapp.model.exceptions.BadRequestException;
import com.project.sopmmobileapp.model.exceptions.LoginException;

import java.net.HttpURLConnection;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.http.Body;

import static io.reactivex.Single.error;
import static io.reactivex.Single.just;

public class UserDetailsClient extends BaseClient {

    @Named("auth")
    @Inject
    Retrofit retrofit;

    private UserDetailsDao userDetailsDao;
    public UserDetailsClient() {
        VoteApplication.getRetrofitComponent().inject(this);
        this.userDetailsDao = retrofit.create(UserDetailsDao.class);
    }

    public Single<BaseResponse> save(@Body final UserDetails userDetails) {
            Log.i("send", userDetails.toString());
        return async(this.userDetailsDao.updateData(userDetails)
                .flatMap(authenticationResponse -> {
                    if (authenticationResponse.isSuccessful()) {
                        return just(Objects.requireNonNull(authenticationResponse.body()));
                    }
                    if (authenticationResponse.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                        return error((new LoginException()));
                    }
                    if (authenticationResponse.code() == HttpURLConnection.HTTP_NOT_FOUND) {
                        return error(new BadRequestException());
                    }
                    return error(new RuntimeException(Objects.requireNonNull(authenticationResponse.errorBody()).toString()));
                }));
    }

}
