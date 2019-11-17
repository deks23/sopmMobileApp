package com.project.sopmmobileapp.model.di.clients;

import android.util.Log;

import com.project.sopmmobileapp.applications.VoteApplication;
import com.project.sopmmobileapp.model.daos.SurveyDao;
import com.project.sopmmobileapp.model.exceptions.BadRequestException;
import com.project.sopmmobileapp.model.exceptions.UserIsTakenException;
import com.project.sopmmobileapp.model.request.CreateSurvey;
import com.project.sopmmobileapp.model.request.NeightborhoodRequest;
import com.project.sopmmobileapp.model.response.BaseResponse;
import com.project.sopmmobileapp.model.response.SurveysResponse;

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
                        return error(new BadRequestException());
                    }
                    return error(new RuntimeException(Objects.requireNonNull(authenticationResponse.errorBody()).toString()));
                }));
    }


    public Single<SurveysResponse> getMySurveys() {
        return async(this.surveyDao.getMySurveys()
                .flatMap(authenticationResponse -> {
                    if (authenticationResponse.isSuccessful()) {
                        Log.i("GetMySurveys", authenticationResponse.toString());
                        return just(Objects.requireNonNull(authenticationResponse.body()));
                    }
                    if (authenticationResponse.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                        Log.i("GetMySurveysError", authenticationResponse.toString());
                        return error(new BadRequestException());
                    }
                    Log.i("GetMySurveysCriticalError", authenticationResponse.toString());
                    return error(new RuntimeException(Objects.requireNonNull(authenticationResponse.errorBody()).toString()));
                }));
    }

    public Single<SurveysResponse> getNotAnswered() {
        return async(this.surveyDao.getNotAnswered()
                .flatMap(authenticationResponse -> {
                    if (authenticationResponse.isSuccessful()) {
                        Log.i("getNotAnswered", authenticationResponse.toString());
                        return just(Objects.requireNonNull(authenticationResponse.body()));
                    }
                    if (authenticationResponse.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                        Log.i("getNotAnsweredError", authenticationResponse.toString());
                        return error(new BadRequestException());
                    }
                    Log.i("getNotAnsweredCriticalError", authenticationResponse.toString());
                    return error(new RuntimeException(Objects.requireNonNull(authenticationResponse.errorBody()).toString()));
                }));
    }

    public Single<SurveysResponse> getSurveysFromNeighborhood(NeightborhoodRequest request) {
        return async(this.surveyDao.getSurveysFromNeighborhood(request)
                .flatMap(authenticationResponse -> {
                    if (authenticationResponse.isSuccessful()) {
                        Log.i("GetNeighborhood", authenticationResponse.toString());
                        return just(Objects.requireNonNull(authenticationResponse.body()));
                    }
                    if (authenticationResponse.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                        Log.i("GetNeighborhoodError", authenticationResponse.toString());
                        return error(new BadRequestException());
                    }
                    Log.i("GetNeighborhoodCriticalError", authenticationResponse.toString());
                    return error(new RuntimeException(Objects.requireNonNull(authenticationResponse.errorBody()).toString()));
                }));
    }
}
