package com.project.sopmmobileapp.model.di.clients;

import com.project.sopmmobileapp.applications.VoteApplication;
import com.project.sopmmobileapp.model.daos.SurveyDao;
import com.project.sopmmobileapp.model.exceptions.UserIsTakenException;
import com.project.sopmmobileapp.model.request.CreateSurvey;
import com.project.sopmmobileapp.model.response.BaseResponse;

import java.net.HttpURLConnection;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import retrofit2.Retrofit;

import static io.reactivex.Single.error;
import static io.reactivex.Single.just;

public class SurveyClient extends BaseClient {

    @Named("auth")
    @Inject
    Retrofit retrofit;

    private SurveyDao surveyDao;

    public SurveyClient() {
        VoteApplication.getRetrofitComponent().inject(this);
        this.surveyDao = retrofit.create(SurveyDao.class);
    }

    public Single<BaseResponse> addSurvey(CreateSurvey createSurvey) {
        return async(this.surveyDao.addSurvey(createSurvey)
                .flatMap(authenticationResponse -> {
                    if (authenticationResponse.isSuccessful()) {
                        return just(Objects.requireNonNull(authenticationResponse.body()));
                    }
                    if (authenticationResponse.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                        return error(new UserIsTakenException());
                    }
                    return error(new RuntimeException(Objects.requireNonNull(authenticationResponse.errorBody()).toString()));
                }));
    }


}
