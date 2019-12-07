package com.project.sopmmobileapp.model.di.clients;

import com.project.sopmmobileapp.applications.VoteApplication;
import com.project.sopmmobileapp.model.daos.StatsDao;
import com.project.sopmmobileapp.model.exceptions.BadRequestException;
import com.project.sopmmobileapp.model.response.StatResponse;
import com.project.sopmmobileapp.model.response.StatsResponse;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.Retrofit;

import static io.reactivex.Single.error;
import static io.reactivex.Single.just;

public class StatsClient extends BaseClient{

    @Named("auth")
    @Inject
    Retrofit retrofit;

    private StatsDao statsDao;

    public StatsClient() {
        VoteApplication.getRetrofitComponent().inject(this);
        this.statsDao = retrofit.create(StatsDao.class);
    }

    public Single<StatsResponse> getStatsFromSurvey(Long surveyId) {
        return async(this.statsDao.getStats(surveyId)
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
}
